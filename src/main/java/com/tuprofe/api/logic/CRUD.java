/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic;

/**
 *
 * @author diegoprietotorres
 */
public interface CRUD<T, PK> {

    public void create(T entity);
}
