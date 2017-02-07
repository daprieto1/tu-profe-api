package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.TuProfeAPIException;
import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.entities.Training;
import com.tuprofe.api.entities.enums.EnumTeacherState;
import com.tuprofe.api.logic.services.IS3Services;
import com.tuprofe.api.logic.services.ITeacherServices;
import com.tuprofe.api.logic.services.ITrainingServices;
import com.tuprofe.api.persistance.repositories.ITeacherRepository;
import java.io.IOException;
import java.util.List;
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

    @Autowired
    @Qualifier("TrainingServices")
    private ITrainingServices trainingServices;

    @Override
    public Teacher create(Teacher teacher) {
        if (teacher == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NULL_PARAM);
        } else if (teacher.getState() == null) {
            throw new TuProfeAPIException(TuProfeAPIException.BAD_TEACHER_STATE);
        } else if (teacherRepository.findByEmail(teacher.getEmail()) != null) {
            throw new TuProfeAPIException(TuProfeAPIException.TEACHER_ALREADY_EXISTS);
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
    public Teacher update(Teacher teacher) {
        if (teacher == null) {
            throw new TuProfeAPIException(TuProfeAPIException.EMPTY_PARAM);
        }
        teacher.setPassword(null);
        teacher.setEmail(null);
        return teacherRepository.update(null, teacher);
    }

    @Override
    public void delete(Teacher entity) {
        throw new UnsupportedOperationException("Not supported."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Teacher> finadAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void updatePassword(String teacherId, String password) {
        Teacher teacher = find(teacherId);
        teacher.setPassword(password);
        teacherRepository.update(teacherId, teacher);
    }

    @Override
    public void activateAccount(String teacherId) {
        Teacher teacher = find(teacherId);
        if (!teacher.isAcceptGameRules()) {
            throw new TuProfeAPIException(TuProfeAPIException.GAME_RULES_NOT_ACCEPTED);
        } else if (!teacher.getExam().isPassExam()) {
            throw new TuProfeAPIException(TuProfeAPIException.EXAM_NOT_PASS);
        } else if (teacher.getState() != EnumTeacherState.INACTIVE.getId()) {
            throw new TuProfeAPIException(TuProfeAPIException.BAD_TEACHER_STATE);
        } else {
            teacher.setState(EnumTeacherState.ACTIVE.getId());
            update(teacher);
        }
    }

    @Override
    public void acceptGameRules(String teacherId) {
        Teacher teacher = find(teacherId);
        if (teacher.isAcceptGameRules()) {
            throw new TuProfeAPIException(TuProfeAPIException.GAME_RULES_ACCEPTED);
        } else {
            teacher.setAcceptGameRules(true);
            update(teacher);
        }
    }

    @Override
    public void takeExam(String teacherId, Teacher.Exam exam) {
        Teacher teacher = find(teacherId);
        Training training = trainingServices.find(exam.getIdExam());

        if (exam.getCorrectAnswers() >= training.getPassLimit()) {
            exam.setPassExam(true);
        } else {
            exam.setPassExam(false);
            teacher.setState(EnumTeacherState.INACTIVE.getId());
        }

        teacher.setExam(exam);
        update(teacher);
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
