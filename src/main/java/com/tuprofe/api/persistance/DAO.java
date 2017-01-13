/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.persistance;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author diego
 */
public interface DAO<T extends Serializable, PK> {

    public List<T> findAll();

    public T find(PK id);

    public T save(T entity);

    public void update(PK id, String field, Object value);

    public void delete(T entity);
}
