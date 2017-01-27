/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.implementation;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.tuprofe.api.entities.EmailSQS;
import com.tuprofe.api.logic.services.ISQSServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
@Qualifier("SQSServices")
public class SQSServices implements ISQSServices {

    @Autowired
    private AmazonSQS amazonSQS;

    @Value("${amazon.sqs.tuprofe.emails}")
    private String emailsQueue;

    @Override
    public void send(EmailSQS email) {
        SendMessageRequest sendMessageRequest = new SendMessageRequest(emailsQueue, "funciona");
        amazonSQS.sendMessage(sendMessageRequest);
    }

}
