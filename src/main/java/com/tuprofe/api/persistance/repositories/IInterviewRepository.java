/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.persistance.repositories;

import com.tuprofe.api.entities.Interview;
import com.tuprofe.api.persistance.DAO;
import java.util.List;

/**
 *
 * @author diego
 */
public interface IInterviewRepository extends DAO<Interview, String> {

    public List<Interview> findAll(Long current);

}
