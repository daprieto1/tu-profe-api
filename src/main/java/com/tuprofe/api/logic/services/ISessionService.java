package com.tuprofe.api.logic.services;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.entities.Token;

/**
 *
 * @author diegoprietotorres
 */
public interface ISessionService {
    
    public Token authenticateTeacher(String username, String password);
    
    public Teacher signUpTeacher(Teacher teacher);
    
    public Teacher getAuthenticatedTeacher(String user);
    
    public void forgotPasswordTeacher(String email);
    
    public void changePasswordTeacher(String nonce, String password);
}
