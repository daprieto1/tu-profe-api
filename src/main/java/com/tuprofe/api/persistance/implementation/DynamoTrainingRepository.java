package com.tuprofe.api.persistance.implementation;

import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.tuprofe.api.entities.Training;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.ITrainingRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
@Qualifier("DynamoTrainingRepository")
public class DynamoTrainingRepository extends DAODynamo<Training, String> implements ITrainingRepository {

    public DynamoTrainingRepository() {
        super(Training.class);
    }

}
