package com.tuprofe.logic;

import com.tuprofe.api.entities.Interview;
import com.tuprofe.api.logic.services.IInterviewServices;
import java.util.Date;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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
public class TestInterviewServices {

    @Autowired
    @Qualifier("InterviewServices")
    private IInterviewServices interviewServices;

    public static Interview createInterviewTemplate() {
        Interview interview = new Interview();

        interview.setCapacity(2);
        interview.setDuration(120);
        interview.setStartDateTime(new Date());

        return interview;
    }

    @Test
    public void testCreate() {
        try {
            Interview interview1 = createInterviewTemplate();
            interviewServices.create(interview1);

            assertNotNull("The interview ID must not be null", interview1.getId());

            Interview interview2 = interviewServices.find(interview1.getId());

            assertNotNull("The teacher 2 must not be null", interview2);
            assertTrue("The teacher id must be equal", interview2.getId().equals(interview2.getId()));
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testCreate : " + e.getMessage();
            fail(fail);
        }
    }
}
