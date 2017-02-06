/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.TuProfeAPIException;
import com.tuprofe.api.entities.Interview;
import com.tuprofe.api.logic.services.IInterviewServices;
import com.tuprofe.api.persistance.repositories.IInterviewRepository;
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
    public Interview update(Interview entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Interview entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Interview> finadAll() {
        return interviewRepository.findAll();
    }

}
