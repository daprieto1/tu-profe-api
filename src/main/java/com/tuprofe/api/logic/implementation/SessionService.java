/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.logic.services.ISessionService;
import com.tuprofe.api.logic.services.ITeacherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author diegoprietotorres
 */
public class SessionService implements ISessionService {
    
    @Autowired
    @Qualifier("TeacherServices")
    ITeacherServices teacherServices;
    
    @Override
    public void signUpTeacher(Teacher teacher) {
        teacherServices.create(teacher);
    }
    
}
