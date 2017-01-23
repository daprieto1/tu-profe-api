/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.services;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.entities.Token;
import java.util.UUID;

/**
 *
 * @author diegoprietotorres
 */
public interface ISessionService {
    
    public Token authenticateTeacher(String username, String password);
    
    public Teacher signUpTeacher(Teacher teacher);
    
    public Teacher getAuthenticatedTeacher(String user);
}
