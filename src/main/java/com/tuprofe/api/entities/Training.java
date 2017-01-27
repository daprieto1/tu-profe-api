package com.tuprofe.api.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshalling;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBNativeBoolean;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author diego
 */
@DynamoDBTable(tableName = "training")
public class Training implements Serializable {

    private Integer id;
    private String type;
    private List<Question> questions;

    public Training() {
    }

    public Training(Integer id, String type, List<Question> questions) {
        this.id = id;
        this.type = type;
        this.questions = questions;
    }

    @DynamoDBHashKey(attributeName = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @DynamoDBAttribute(attributeName = "questions")
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public class Question {

        private String questionText;
        private List<Answer> answers;

        public Question() {
        }

        public Question(String questionText, List<Answer> answers) {
            this.questionText = questionText;
            this.answers = answers;
        }

        @DynamoDBAttribute(attributeName = "questionText")
        public String getQuestionText() {
            return questionText;
        }

        public void setQuestionText(String questionText) {
            this.questionText = questionText;
        }

        @DynamoDBAttribute(attributeName = "answers")
        public List<Answer> getAnswers() {
            return answers;
        }

        public void setAnswers(List<Answer> answers) {
            this.answers = answers;
        }

    }

    public class Answer {

        private String answerText;
        private boolean isValid;

        public Answer() {
        }

        public Answer(String answerText, boolean isValid) {
            this.answerText = answerText;
            this.isValid = isValid;
        }

        @DynamoDBAttribute(attributeName = "answerText")
        public String getAnswerText() {
            return answerText;
        }

        public void setAnswerText(String answerText) {
            this.answerText = answerText;
        }
        
        @DynamoDBNativeBoolean
        @DynamoDBAttribute(attributeName = "isValid")
        public boolean isIsValid() {
            return isValid;
        }

        public void setIsValid(boolean isValid) {
            this.isValid = isValid;
        }

    }
}
