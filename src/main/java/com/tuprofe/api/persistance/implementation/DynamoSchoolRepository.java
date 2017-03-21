package com.tuprofe.api.persistance.implementation;

import com.tuprofe.api.entities.School;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.ISchoolRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diegoprietotorres
 */
@Repository
@Qualifier("DynamoSchoolRepository")
public class DynamoSchoolRepository extends DAODynamo<School, String> implements ISchoolRepository {

    public DynamoSchoolRepository() {
        super(School.class);
    }
}
