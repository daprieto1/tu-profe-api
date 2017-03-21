package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.TuProfeAPIException;
import com.tuprofe.api.entities.School;
import com.tuprofe.api.logic.services.ISchoolServices;
import com.tuprofe.api.persistance.repositories.ISchoolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author diegoprietotorres
 */
@Service
@Qualifier("SchoolServices")
public class SchoolServices implements ISchoolServices {

    @Autowired
    @Qualifier("DynamoSchoolRepository")
    private ISchoolRepository schoolRepository;

    @Override
    public School create(School school) {
        if(school == null){
            throw new TuProfeAPIException(TuProfeAPIException.NULL_PARAM);
        }
        
        School result = schoolRepository.save(school);
        
        return result;
    }

    @Override
    public School find(String id) {
        if (id == null || "".equals(id)) {
            throw new TuProfeAPIException(TuProfeAPIException.EMPTY_PARAM);
        }

        School school = schoolRepository.find(id);

        if (school == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NOT_FIND_ENTITY);
        }

        return school;
    }

    @Override
    public School update(School entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(School entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<School> finadAll() {
        return schoolRepository.findAll();
    }

}
