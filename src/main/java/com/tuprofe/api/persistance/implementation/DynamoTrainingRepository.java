/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.persistance.implementation;

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
public class DynamoTrainingRepository extends DAODynamo<Training, Integer> implements ITrainingRepository {

    public DynamoTrainingRepository() {
        super(Training.class);
    }
}
