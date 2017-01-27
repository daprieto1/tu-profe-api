/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.logic;

import com.tuprofe.api.logic.services.IEmailServices;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author diegoprietotorres
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestSendGridServices {
    
    @Autowired
    @Qualifier("SendGridServices")
    private IEmailServices emailServices;
    
    @Test
    public void testSendEmail() {
        try {
            emailServices.sendSignUpTeacheMail(null);
        } catch (Exception e) {
            System.err.println(e.getCause() + " - " + e.getMessage());
            String fail = "FAIL = testSendEmail : " + e.getMessage();
            fail(fail);
        }
    }
}
