package com.tuprofe.api.logic.implementation;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.tuprofe.api.TuProfeAPIException;
import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.entities.enums.EnumTeacherState;
import com.tuprofe.api.logic.services.IS3Services;
import com.tuprofe.api.logic.services.ITeacherServices;
import com.tuprofe.api.persistance.repositories.ITeacherRepository;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public void uploadCurriculum(MultipartFile file, String teacherId) {
        try {
            Teacher teacher = this.find(teacherId);
            String key = teacher.getId() + ".docx";
            s3Services.uploadFile(file, curriculumBucket, key);
            teacher.setState(EnumTeacherState.CURRICULUM);
            this.update(teacher);
        } catch (IOException ex) {
            throw new TuProfeAPIException(ex.getMessage(), ex.getCause());
        }

    }

}
