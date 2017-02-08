package com.tuprofe.api.resources;

import com.tuprofe.api.entities.Course;
import com.tuprofe.api.logic.services.ICourseServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author diegoprietotorres
 */
@RestController
@RequestMapping("/course")
public class CourseResource {

    @Autowired
    @Qualifier("CourseServices")
    private ICourseServices courseServices;

    @RequestMapping(method = RequestMethod.GET)
    public List<Course> getAll() {
        return courseServices.finadAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Course create(@RequestBody Course course) {
        return courseServices.create(course);
    }
}
