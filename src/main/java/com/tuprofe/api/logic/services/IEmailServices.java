/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.services;

import com.tuprofe.api.entities.Email;
import com.tuprofe.api.entities.Teacher;

/**
 *
 * @author diegoprietotorres
 */
public interface IEmailServices {
    public void sendEmail(Email email);
    
    public void sendSignUpTeacheMail(Teacher teacher);
    
    public void sendForgotPasswordTeacheMail(String nonce, String email);
}
