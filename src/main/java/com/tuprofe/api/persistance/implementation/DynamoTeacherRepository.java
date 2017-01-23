package com.tuprofe.api.persistance.implementation;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.ITeacherRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diegoprietotorres
 */
@Repository
@Qualifier("DynamoTeacherRepository")
public class DynamoTeacherRepository extends DAODynamo<Teacher, String> implements ITeacherRepository {

    public DynamoTeacherRepository() {
        super(Teacher.class);
    }

    @Override
    public Teacher findByEmail(String email) {
        Teacher teacher = null;
        AmazonDynamoDB dynamoDB = getDynamoDB();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(email));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("email = :val1")
                .withExpressionAttributeValues(eav);

        List<Teacher> teachers = mapper.scan(Teacher.class, scanExpression);

        if (teachers.size() > 0) {
            teacher = teachers.get(0);
        }

        return teacher;

    }
}
