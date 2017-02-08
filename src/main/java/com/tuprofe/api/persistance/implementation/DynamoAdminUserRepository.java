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
import com.tuprofe.api.entities.AdminUser;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.IAdminUserRepository;
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
@Qualifier("DynamoAdminUserRepository")
public class DynamoAdminUserRepository extends DAODynamo<AdminUser, String> implements IAdminUserRepository {

    public DynamoAdminUserRepository() {
        super(AdminUser.class);
    }

    @Override
    public AdminUser findByEmail(String email) {
        AdminUser adminUser = null;
        AmazonDynamoDB dynamoDB = getDynamoDB();
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":val1", new AttributeValue().withS(email));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("email = :val1")
                .withExpressionAttributeValues(eav);

        List<AdminUser> adminUsers = mapper.scan(AdminUser.class, scanExpression);

        if (adminUsers.size() > 0) {
            adminUser = adminUsers.get(0);
        }

        return adminUser;
    }

}
