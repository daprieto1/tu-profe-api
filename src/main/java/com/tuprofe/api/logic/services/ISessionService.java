package com.tuprofe.api.logic.services;

import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.entities.Token;
import com.tuprofe.api.entities.User;

/**
 *
 * @author diegoprietotorres
 */
public interface ISessionService {

    public Token authenticate(Class c, String username, String password);

    public User signUp(Class c, User user);

    public Token authenticateTeacher(String username, String password);

    public Teacher signUpTeacher(Teacher teacher);

    public Teacher getAuthenticatedTeacher(String user);

    public void forgotPasswordTeacher(String email);

    public void changePasswordTeacher(String nonce, String password);
}
