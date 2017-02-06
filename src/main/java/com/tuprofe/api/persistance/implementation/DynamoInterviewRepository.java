/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.persistance.implementation;

import com.tuprofe.api.entities.Course;
import com.tuprofe.api.entities.Interview;
import com.tuprofe.api.persistance.engine.DAODynamo;
import com.tuprofe.api.persistance.repositories.IInterviewRepository;
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
}
