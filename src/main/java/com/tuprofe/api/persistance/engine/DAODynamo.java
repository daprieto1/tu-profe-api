/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.persistance.engine;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.tuprofe.api.persistance.DAO;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author diegoprietotorres
 */
public class DAODynamo<T extends Serializable, PK> implements DAO<T, PK> {

    private Class<T> baseClass;

    @Autowired
    private AmazonDynamoDB dynamoDB;

    public DAODynamo() {
        super();
    }

    public DAODynamo(Class<T> baseClass) {
        super();
        this.baseClass = baseClass;
    }

    @Override
    public List<T> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T find(PK id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public T save(T entity) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        mapper.save(entity);
        return null;
    }

    @Override
    public void update(PK id, String field, Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(T entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    protected AmazonDynamoDB getDynamoDB() {
        return dynamoDB;
    }

    public void setDynamoDB(AmazonDynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

}
