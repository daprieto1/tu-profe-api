package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.entities.University;
import com.tuprofe.api.logic.services.IUniversityServices;
import com.tuprofe.api.persistance.repositories.IUniversityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author diegoprietotorres
 */
@Service
@Qualifier("UniversityServices")
public class UniversityServices implements IUniversityServices {

    @Autowired
    @Qualifier("DynamoUniversityRepository")
    private IUniversityRepository universityRepository;

    @Override
    public University create(University entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public University find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public University update(University entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(University entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<University> finadAll() {
        return universityRepository.findAll();
    }

}
