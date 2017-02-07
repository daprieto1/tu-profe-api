/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.resources;

import com.tuprofe.api.entities.Training;
import com.tuprofe.api.logic.services.ITrainingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/training")
public class TrainingResource {
    
    @Autowired
    @Qualifier("TrainingServices")
    private ITrainingServices trainingServices;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Training get(@PathVariable("id") String id) {
        return trainingServices.find(id);
    }
}
