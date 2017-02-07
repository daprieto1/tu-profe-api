/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.persistance.implementation;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.tuprofe.api.entities.Course;
import com.tuprofe.api.entities.Interview;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.IInterviewRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
@Qualifier("DynamoInterviewRepository")
public class DynamoInterviewRepository extends DAODynamo<Interview, String> implements IInterviewRepository {

    public DynamoInterviewRepository() {
        super(Interview.class);
    }

    @Override
    public List<Interview> findAll(Long current) {
        AmazonDynamoDB dynamoDB = getDynamoDB();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withN(current.toString()));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("startDateTime >= :val1")
                .withExpressionAttributeValues(eav);

        List<Interview> interviews = mapper.scan(Interview.class, scanExpression);

        return interviews;
    }
}
