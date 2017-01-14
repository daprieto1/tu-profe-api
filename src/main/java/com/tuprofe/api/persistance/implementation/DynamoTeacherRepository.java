package com.tuprofe.api.persistance.implementation;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.ITeacherRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diegoprietotorres
 */
@Repository
@Qualifier("DynamoTeacherRepository")
public class DynamoTeacherRepository extends DAODynamo<Teacher, String> implements ITeacherRepository {

    public DynamoTeacherRepository() {
        super(Teacher.class);
    }
}
