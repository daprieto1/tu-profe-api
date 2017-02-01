package com.tuprofe.api.persistance.implementation;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuprofe.api.entities.Training;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.ITrainingRepository;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
@Qualifier("DynamoTrainingRepository")
public class DynamoTrainingRepository extends DAODynamo<Training, String> implements ITrainingRepository {

    @Autowired
    private AWSCredentials amazonAWSCredentials;

    @Autowired
    private ObjectMapper objectMapper;

    public DynamoTrainingRepository() {
        super(Training.class);
    }

    /**
     * @Override public Training find(String id) { AmazonDynamoDBClient
     * amazonDynamoDBClient = new AmazonDynamoDBClient(amazonAWSCredentials);
     * amazonDynamoDBClient.setRegion(Region.getRegion(Regions.US_WEST_2));
     * DynamoDB dynamoDB = new DynamoDB(amazonDynamoDBClient);
     *
     * GetItemSpec spec = new GetItemSpec() .withPrimaryKey("id", id); Table
     * table = dynamoDB.getTable("training"); Item item = table.getItem(spec);
     * Training training = null; System.out.println(item.toJSONPretty()); try {
     * training = objectMapper.readValue(item.toJSON(), Training.class); } catch
     * (IOException ex) {
     * Logger.getLogger(DynamoTrainingRepository.class.getName()).log(Level.SEVERE,
     * null, ex); }
     *
     * return training;
    }
     */
}
