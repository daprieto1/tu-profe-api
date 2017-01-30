package com.tuprofe.api.persistance.implementation;

import com.tuprofe.api.entities.Course;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.ICourseRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diegoprietotorres
 */
@Repository
@Qualifier("DynamoCourseRepository")
public class DynamoCourseRepository extends DAODynamo<Course, String> implements ICourseRepository {

    public DynamoCourseRepository() {
        super(Course.class);
    }
}
