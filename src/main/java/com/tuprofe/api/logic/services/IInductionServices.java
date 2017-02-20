/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.services;

import com.tuprofe.api.entities.Induction;
import com.tuprofe.api.logic.CRUD;
import java.util.List;

/**
 *
 * @author diego
 */
public interface IInductionServices extends CRUD<Induction, String> {

    public List<Induction> getActive();

    public void takePlace(String teacherId, String interviewId);
}
