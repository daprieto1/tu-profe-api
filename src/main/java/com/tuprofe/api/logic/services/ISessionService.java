/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.services;

import com.tuprofe.api.entities.Teacher;

/**
 *
 * @author diegoprietotorres
 */
public interface ISessionService {
    
    public Teacher signUpTeacher(Teacher teacher);
}
