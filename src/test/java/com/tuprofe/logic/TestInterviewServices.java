package com.tuprofe.logic;

import com.tuprofe.api.entities.Interview;
import com.tuprofe.api.logic.services.IInterviewServices;
import com.tuprofe.api.persistance.repositories.IInterviewRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
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

    private List<Interview> createdInterviews;

    @Autowired
    @Qualifier("InterviewServices")
    private IInterviewServices interviewServices;

    @Autowired
    @Qualifier("DynamoInterviewRepository")
    private IInterviewRepository interviewRepository;

    public static Interview createInterviewTemplate() {
        Interview interview = new Interview();

        interview.setCapacity(2);
        interview.setDuration(120);
        interview.setStartDateTime((new Date()).getTime());

        return interview;
    }

    @Before
    public void initTest() {
        createdInterviews = new ArrayList<>();
    }

    @Test
    public void testCreate() {
        try {
            Interview interview1 = createInterviewTemplate();
            interviewServices.create(interview1);
            createdInterviews.add(interview1);

            assertNotNull("The interview ID must not be null", interview1.getId());

            Interview interview2 = interviewServices.find(interview1.getId());

            assertNotNull("The interview 2 must not be null", interview2);
            assertTrue("The interviews id's must be equal", interview2.getId().equals(interview2.getId()));
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testCreate : " + e.getMessage();
            fail(fail);
        }
    }

    @Test
    public void testGetActive() {
        try {
            Interview interview1 = createInterviewTemplate();
            interview1.setStartDateTime(interview1.getStartDateTime() + new Long("10000000000"));
            interviewServices.create(interview1);
            //createdInterviews.add(interview1);
            
            Interview interview2 = createInterviewTemplate();
            interview2.setStartDateTime(interview2.getStartDateTime() - 10000);
            interviewServices.create(interview2);
            createdInterviews.add(interview2);

            List<Interview> interviews = interviewServices.getActive();
            assertNotNull("The interviews array must not be null", interviews);
            assertTrue("The interviews size must be greter than 0", interviews.size() > 0);
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testGetActive : " + e.getMessage();
            fail(fail);
        }
    }

    @After
    public void tearDown() {
        createdInterviews.forEach(i -> interviewRepository.delete(i));
    }
}
