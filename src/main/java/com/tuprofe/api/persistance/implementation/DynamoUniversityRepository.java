package com.tuprofe.api.persistance.implementation;

import com.tuprofe.api.entities.University;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.IUniversityRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diegoprietotorres
 */
@Repository
@Qualifier("DynamoUniversityRepository")
public class DynamoUniversityRepository extends DAODynamo<University, String> implements IUniversityRepository {

    public DynamoUniversityRepository() {
        super(University.class);
    }
}
