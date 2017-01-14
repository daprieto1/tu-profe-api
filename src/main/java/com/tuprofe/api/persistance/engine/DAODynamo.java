package com.tuprofe.api.persistance.engine;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.SaveBehavior;
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
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        T t = mapper.load(baseClass, id);
        return t;
    }

    @Override
    public T save(T entity) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        mapper.save(entity);
        return entity;
    }

    @Override
    public T update(PK id, T entity) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);        
        mapper.save(entity, SaveBehavior.UPDATE_SKIP_NULL_ATTRIBUTES.config());
        return entity;
    }

    @Override
    public void delete(T entity) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDB);
        mapper.delete(entity);
    }

    protected AmazonDynamoDB getDynamoDB() {
        return dynamoDB;
    }

    public void setDynamoDB(AmazonDynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

}
