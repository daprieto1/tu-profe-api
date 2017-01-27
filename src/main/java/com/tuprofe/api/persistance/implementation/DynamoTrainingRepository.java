package com.tuprofe.api.persistance.implementation;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.tuprofe.api.entities.Training;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.ITrainingRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
@Qualifier("DynamoTrainingRepository")
public class DynamoTrainingRepository extends DAODynamo<Training, Integer> implements ITrainingRepository {

    public DynamoTrainingRepository() {
        super(Training.class);
    }

    @Override
    public Training find(Integer id) {

        GetItemRequest gir = new GetItemRequest("training", null);
        GetItemResult item = this.getDynamoDB().getItem(gir);
        return null;
    }
}
