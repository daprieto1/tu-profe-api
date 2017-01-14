package com.tuprofe.logic;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.logic.services.ITeacherServices;
import com.tuprofe.api.persistance.repositories.ITeacherRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Ignore;
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

    public static final String TEACHER_IDENTIFICATION = "TEACHER_IDENTIFICATION";

    private List<Teacher> teachers;

    @Autowired
    @Qualifier("TeacherServices")
    private ITeacherServices teacherServices;

    @Autowired
    @Qualifier("DynamoTeacherRepository")
    ITeacherRepository teacherRepository;

    public static Teacher createTeacherTemplate() {
        Teacher teacher = new Teacher(TEACHER_NAME, TEACHER_LAST_NAME, TEACHER_EMAIL, TEACHER_PASSWORD);
        return teacher;
    }

    @Before
    public void initTest() {
        teachers = new ArrayList<>();
    }

    @Test
    public void testCreate() {
        try {
            Teacher teacher1 = TestTeacherService.createTeacherTemplate();
            teacher1 = teacherServices.create(teacher1);
            teachers.add(teacher1);

            assertNotNull("The teacher ID must not be null", teacher1.getId());

            Teacher teacher2 = teacherServices.find(teacher1.getId());

            assertNotNull("The teacher 2 must not be null", teacher2);
            assertTrue("The teacher id must be equal", teacher1.getId().equals(teacher2.getId()));
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testCreate : " + e.getMessage();
            fail(fail);
        }
    }

    @Test
    public void testUpdate() {
        try {
            Teacher teacher1 = TestTeacherService.createTeacherTemplate();
            teacher1 = teacherServices.create(teacher1);
            teachers.add(teacher1);

            assertNotNull("The teacher ID must not be null", teacher1.getId());

            teacher1.setIdentification(TestTeacherService.TEACHER_IDENTIFICATION);
            teacherServices.update(teacher1);

            Teacher teacher2 = teacherServices.find(teacher1.getId());
            assertNotNull("The teacher 2 must not be null", teacher2);
            assertTrue("The teacher id must be equal", teacher1.getId().equals(teacher2.getId()));
            assertTrue("The teacher identification must be equal", teacher1.getIdentification().equals(teacher2.getIdentification()));
            assertTrue("The teacher name must be equal", teacher1.getName().equals(teacher2.getName()));
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testUpdate : " + e.getMessage();
            fail(fail);
        }
    }

    @Test
    @Ignore
    public void testUploadCurriculum() {
        try {
            //teacherServices.uploadCurriculum();
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testUploadCurriculum : " + e.getMessage();
            fail(fail);
        }
    }

    @After
    public void tearDown() {
        teachers.forEach(t -> teacherRepository.delete(t));
    }
}
