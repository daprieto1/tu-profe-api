package com.tuprofe.persistance;

import com.tuprofe.api.entities.Training;
import com.tuprofe.api.persistance.repositories.ITrainingRepository;
import java.util.ArrayList;
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
 * @author diego
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestDynamoTrainingRepository {

    @Autowired
    @Qualifier("DynamoTrainingRepository")
    private ITrainingRepository trainingRepository;
        
    @Test
    public void testFindAll() {
        try {
            Training training = trainingRepository.find("cb05f5c2-11f3-473d-bc68-1f9e5b1c4ec2");

            assertNotNull("The traings must not be null", training);
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testFindAll : " + e.getMessage();
            fail(fail);
        }
    }
    /**
    public void testSave() {
        try {
            Training training = new Training();

            List<Training.Answer> answers = new ArrayList<>();
            answers.add(training.new Answer("answerText", true));
            answers.add(training.new Answer("answerText", false));

            List<Training.Question> questions = new ArrayList<>();
            questions.add(training.new Question("questionText", answers));
            
            training.setType("test");
            training.setQuestions(questions);

            trainingRepository.save(training);

        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testSave : " + e.getMessage();
            fail(fail);
        }
    }
    * */
}


