package com.tuprofe.api.logic.implementation;

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
    public School create(School entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public School find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
