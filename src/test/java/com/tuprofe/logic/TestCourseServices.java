/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.logic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuprofe.api.entities.Course;
import com.tuprofe.api.logic.services.ICourseServices;
import java.util.List;
import static org.junit.Assert.fail;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author diego
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestCourseServices {

    @Autowired
    @Qualifier("CourseServices")
    private ICourseServices courseServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Ignore
    public void test() {
        try {
            List<Course> courses = objectMapper.readValue("", objectMapper.getTypeFactory().constructCollectionType(List.class, Course.class));
            courses.forEach((course) -> {
                courseServices.create(course);
            });
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testCreate : " + e.getMessage();
            fail(fail);
        }
    }
}
