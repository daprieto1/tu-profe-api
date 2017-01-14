/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.implementation;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.entities.User;
import com.tuprofe.api.logic.services.ISessionService;
import com.tuprofe.api.logic.services.ITeacherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author diegoprietotorres
 */
@Service
@Qualifier("SessionService")
public class SessionService implements ISessionService {

    @Autowired
    @Qualifier("TeacherServices")
    ITeacherServices teacherServices;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Teacher signUpTeacher(Teacher teacher) {
        signUpProcess(teacher);
        return teacherServices.create(teacher);
    }

    private void signUpProcess(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

}
