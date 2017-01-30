/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic;

import java.util.List;

/**
 *
 * @author diegoprietotorres
 */
public interface CRUD<T, PK> {
    
    public T create(T entity);
    
    public T find(PK id);
    
    public T update(T entity);
    
    public void delete(T entity);
    
    public List<T> finadAll();
}
