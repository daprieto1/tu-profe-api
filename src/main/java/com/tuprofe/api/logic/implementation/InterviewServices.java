package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.TuProfeAPIException;
import com.tuprofe.api.entities.Interview;
import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.entities.enums.EnumTeacherState;
import com.tuprofe.api.logic.services.IInterviewServices;
import com.tuprofe.api.logic.services.ITeacherServices;
import com.tuprofe.api.persistance.repositories.IInterviewRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
@Qualifier("InterviewServices")
public class InterviewServices implements IInterviewServices {

    public static final Integer MAX_INTERVIEW_CAPACITY = 3;
    public static final Integer MIN_INTERVIEW_CAPACITY = 1;

    @Autowired
    @Qualifier("DynamoInterviewRepository")
    private IInterviewRepository interviewRepository;

    @Autowired
    @Qualifier("TeacherServices")
    private ITeacherServices teacherServices;

    @Override
    public Interview create(Interview interview) {
        if (interview == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NULL_PARAM);
        } else if (MAX_INTERVIEW_CAPACITY < interview.getCapacity() || interview.getCapacity() < MIN_INTERVIEW_CAPACITY) {
            throw new TuProfeAPIException(TuProfeAPIException.WRONG_INTERVIEW_CAPACITY);
        }
        return interviewRepository.save(interview);
    }

    @Override
    public Interview find(String id) {
        if (id == null || "".equals(id)) {
            throw new TuProfeAPIException(TuProfeAPIException.EMPTY_PARAM);
        }

        Interview interview = interviewRepository.find(id);

        if (interview == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NOT_FIND_ENTITY);
        }

        return interview;
    }

    @Override
    public Interview update(Interview interview) {
        if (interview == null) {
            throw new TuProfeAPIException(TuProfeAPIException.EMPTY_PARAM);
        } else if (interview.getInterviewed().size() > interview.getCapacity()) {
            throw new TuProfeAPIException(TuProfeAPIException.INTERVIEW_FULL);
        }
        return interviewRepository.update(null, interview);
    }

    @Override
    public void delete(Interview entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Interview> finadAll() {
        return interviewRepository.findAll();
    }

    @Override
    public List<Interview> getActive() {
        Long currentTime = (new Date()).getTime();
        return interviewRepository.findAll(currentTime);
    }

    @Override
    public void takePlace(String teacherId, String interviewId) {
        Teacher teacher = teacherServices.find(teacherId);
        Interview interview = find(interviewId);

        if (teacher.getInterview() != null) {
            throw new TuProfeAPIException(TuProfeAPIException.TEACHER_WITH_INTERVIEW);
        } else if (interview.getInterviewed().size() >= interview.getCapacity()) {
            throw new TuProfeAPIException(TuProfeAPIException.INTERVIEW_FULL);
        }

        teacher.setInterview(interview.getId());
        teacher.setState(EnumTeacherState.INTERVIEW.getId());
        interview.getInterviewed().add(teacher.getId());

        teacherServices.update(teacher);
        this.update(interview);
    }

}
