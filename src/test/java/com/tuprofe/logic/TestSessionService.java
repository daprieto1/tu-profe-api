package com.tuprofe.logic;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.logic.services.ISessionService;
import com.tuprofe.api.persistance.repositories.ITeacherRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author diegoprietotorres
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestSessionService {

    private List<Teacher> teachers;

    @Autowired
    @Qualifier("SessionService")
    private ISessionService sessionService;

    @Autowired
    @Qualifier("DynamoTeacherRepository")
    ITeacherRepository teacherRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Before
    public void initTest() {
        teachers = new ArrayList<>();
    }

    @Test
    public void testSignUpTeacher() {
        try {
            Teacher teacher1 = TestTeacherService.createTeacherTemplate();
            teacher1 = sessionService.signUpTeacher(teacher1);
            teachers.add(teacher1);

            assertNotNull("The teacher ID must not be null", teacher1.getId());

            Teacher teacher2 = teacherRepository.find(teacher1.getId());
            assertNotNull("The teacher 2 must not be null", teacher2);
            assertTrue("The teacher name must be equal", teacher1.getId().equals(teacher2.getId()));
            assertTrue("The password must be encrypted", bCryptPasswordEncoder.matches(TestTeacherService.TEACHER_PASSWORD, teacher2.getPassword()));
            assertTrue("The teacher state must be signUp", Objects.equals(teacher2.getState(), teacher1.getState()));
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testSignUpTeacher : " + e.getMessage();
            fail(fail);
        }
    }

    @After
    public void tearDown() {
        teachers.forEach(t -> teacherRepository.delete(t));
    }
}
