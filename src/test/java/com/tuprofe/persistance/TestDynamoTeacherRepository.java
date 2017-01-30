package com.tuprofe.persistance;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.persistance.repositories.ITeacherRepository;
import java.util.List;
import static org.junit.Assert.assertNotNull;
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
public class TestDynamoTeacherRepository {

    @Autowired
    @Qualifier("DynamoTeacherRepository")
    private ITeacherRepository teacherRepository;

    @Test
    public void testFinadALL() {
        try {
            List<Teacher> teachers = teacherRepository.findAll();

            assertNotNull("The list of teachers must not be null", teachers);
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testSave : " + e.getMessage();
            fail(fail);
        }
    }
}
