package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.TuProfeAPIException;
import com.tuprofe.api.entities.Induction;
import com.tuprofe.api.logic.services.IInductionServices;
import com.tuprofe.api.persistance.repositories.IInductionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
@Qualifier("InductionServices")
public class InductionServices implements IInductionServices{
    
    public static final Integer MAX_INDUCTION_CAPACITY = 50;
    public static final Integer MIN_INDUCTION_CAPACITY = 1;
    
    @Autowired
    @Qualifier("DynamoInductionRepository")
    private IInductionRepository inductionRepository;

    @Override
    public List<Induction> getActive() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void takePlace(String teacherId, String interviewId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Induction create(Induction induction) {
        if (induction == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NULL_PARAM);
        } else if (MAX_INDUCTION_CAPACITY < induction.getCapacity() || induction.getCapacity() < MIN_INDUCTION_CAPACITY) {
            throw new TuProfeAPIException(TuProfeAPIException.WRONG_INDUCTION_CAPACITY);
        }
        return inductionRepository.save(induction);
    }

    @Override
    public Induction find(String id) {
         if (id == null || "".equals(id)) {
            throw new TuProfeAPIException(TuProfeAPIException.EMPTY_PARAM);
        }

        Induction induction = inductionRepository.find(id);

        if (induction == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NOT_FIND_ENTITY);
        }

        return induction;
    }

    @Override
    public Induction update(Induction induction) {
        if (induction == null) {
            throw new TuProfeAPIException(TuProfeAPIException.EMPTY_PARAM);
        } else if (induction.getAssistants().size() > induction.getCapacity()) {
            throw new TuProfeAPIException(TuProfeAPIException.INDUCTION_FULL);
        }
        return inductionRepository.update(null, induction);
    }

    @Override
    public void delete(Induction entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Induction> finadAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
