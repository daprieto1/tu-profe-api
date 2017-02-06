package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.TuProfeAPIException;
import com.tuprofe.api.entities.Course;
import com.tuprofe.api.logic.services.ICourseServices;
import com.tuprofe.api.persistance.repositories.ICourseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author diegoprietotorres
 */
@Service
@Qualifier("CourseServices")
public class CourseServices implements ICourseServices {

    @Autowired
    @Qualifier("DynamoCourseRepository")
    private ICourseRepository courseRepository;

    @Override
    public Course create(Course course) {
        if (course == null) {
            throw new TuProfeAPIException(TuProfeAPIException.NULL_PARAM);
        }
        return courseRepository.save(course);
    }

    @Override
    public Course find(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Course update(Course entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Course entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Course> finadAll() {
        return courseRepository.findAll();
    }

}
