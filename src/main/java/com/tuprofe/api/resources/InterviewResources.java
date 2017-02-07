/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.resources;

import com.tuprofe.api.entities.Interview;
import com.tuprofe.api.logic.services.IInterviewServices;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/interview")
public class InterviewResources {

    @Autowired
    @Qualifier("InterviewServices")
    private IInterviewServices interviewServices;

    @RequestMapping(method = RequestMethod.GET)
    public List<Interview> getAll() {
        return interviewServices.finadAll();
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Interview create(@RequestBody Interview interview) {
        return interviewServices.create(interview);
    }

    @RequestMapping(value = "/active", method = RequestMethod.GET, produces = "application/json")
    public List<Interview> getAllActive() {
        return interviewServices.getActive();
    }

    @RequestMapping(value = "/take-place", method = RequestMethod.POST, produces = "application/json")
    public void takePlace(@RequestParam("teacherId") String teacherId, @RequestParam("interviewId") String interviewId) {
        interviewServices.takePlace(teacherId, interviewId);
    }
}
