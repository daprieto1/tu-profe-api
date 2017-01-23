package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.TuProfeAPIException;
import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.entities.enums.EnumTeacherState;
import com.tuprofe.api.logic.services.IS3Services;
import com.tuprofe.api.logic.services.ITeacherServices;
import com.tuprofe.api.persistance.repositories.ITeacherRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author diegoprietotorres
 */
@Service
@Qualifier("TeacherServices")
@PropertySource(value = "classpath:aws-config.properties")
public class TeacherServices implements ITeacherServices {

    private static final int DAYS = 7;
    private static final int SECTIONS = 15;

    @Value("${amazon.s3.tuprofe.teacher.curriculum}")
    private String curriculumBucket;

    @Value("${amazon.s3.tuprofe.teacher.photo}")
    private String photoBucket;

    @Autowired
    @Qualifier("DynamoTeacherRepository")
    ITeacherRepository teacherRepository;

    @Autowired
    @Qualifier("S3ServicesClient")
    private IS3Services s3Services;

    @Override
    public Teacher create(Teacher teacher) {
        if (teacher == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NULL_PARAM);
        } else if (teacher.getState() == null) {
            throw new TuProfeAPIException(TuProfeAPIException.BAD_TEACHER_STATE);
        }

        int numberSessions = DAYS * SECTIONS * 2;
        String schedule = "";
        for (int i = 0; i < numberSessions; i++) {
            schedule += "0";
        }

        teacher.setSchedule(schedule);
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher find(String id) {
        if (id == null || "".equals(id)) {
            throw new TuProfeAPIException(TuProfeAPIException.EMPTY_PARAM);
        }

        Teacher teacher = teacherRepository.find(id);

        if (teacher == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NOT_FIND_ENTITY);
        }

        return teacher;
    }

    @Override
    public Teacher update(Teacher entity) {
        entity.setPassword(null);
        entity.setEmail(null);
        return teacherRepository.update(null, entity);
    }

    @Override
    public void delete(Teacher entity) {
        throw new UnsupportedOperationException("Not supported."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Teacher findByEmail(String email) {
        if (email == null || "".equals(email)) {
            throw new TuProfeAPIException(TuProfeAPIException.EMPTY_PARAM);
        }

        Teacher teacher = teacherRepository.findByEmail(email);

        if (teacher == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NOT_FIND_ENTITY);
        }

        return teacher;
    }

    @Override
    public void uploadCurriculum(MultipartFile file, String teacherId) {
        try {
            Teacher teacher = this.find(teacherId);
            String key = teacher.getId() + ".docx";
            s3Services.uploadFile(file, curriculumBucket, key);
            teacher.setState(EnumTeacherState.CURRICULUM.getId());
            this.update(teacher);
        } catch (IOException ex) {
            throw new TuProfeAPIException(ex.getMessage(), ex.getCause());
        }

    }

    @Override
    public void uploadPhoto(MultipartFile file, String teacherId) {
        try {
            Teacher teacher = this.find(teacherId);
            String key = teacher.getId() + ".jpg";
            s3Services.uploadFile(file, photoBucket, key);
        } catch (IOException ex) {
            throw new TuProfeAPIException(ex.getMessage(), ex.getCause());
        }
    }

}
