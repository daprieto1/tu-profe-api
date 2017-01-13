/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.logic.services.ITeacherServices;
import com.tuprofe.api.persistance.repositories.ITeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author diegoprietotorres
 */
@Service
@Qualifier("TeacherServices")
public class TeacherServices implements ITeacherServices{
    
    @Autowired
    @Qualifier("DynamoTeacherRepository")
    ITeacherRepository teacherRepository;
    
    @Override
    public void create(Teacher teacher) {
        teacherRepository.save(teacher);
    }
    
}
