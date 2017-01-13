package com.tuprofe.logic;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.logic.services.ITeacherServices;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author diegoprietotorres
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestTeacherService {

    public static final String TEACHER_NAME = "TEACHER_NAME";
    public static final String TEACHER_LAST_NAME = "TEACHER_LAST_NAME";
    public static final String TEACHER_EMAIL = "TEACHER_EMAIL";
    public static final String TEACHER_PASSWORD = "TEACHER_PASSWORD";
    
    @Autowired
    @Qualifier("TeacherServices")
    private ITeacherServices teacherServices;

    public static Teacher createTeacherTemplate() {
        Teacher teacher = new Teacher(TEACHER_NAME, TEACHER_LAST_NAME, TEACHER_EMAIL, TEACHER_PASSWORD);
        return teacher;
    }
    
    @Test
    public void testCreate(){
        try {
            Teacher teacher = TestTeacherService.createTeacherTemplate();
            teacherServices.create(teacher);
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testCreate : " + e.getMessage();            
            fail(fail);
        }
    }
}
