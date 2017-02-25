/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tuprofe.api.logic.implementation;

import com.sendgrid.*;
import com.tuprofe.api.entities.Email;
import com.tuprofe.api.entities.Teacher;
import com.tuprofe.api.logic.services.IEmailServices;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author diegoprietotorres
 */
@Service
@Qualifier("SendGridServices")
public class SendGridServices implements IEmailServices {

    @Override
    public void sendEmail(Email email) {
        com.sendgrid.Email from = new com.sendgrid.Email("diego@mensajerosurbanos.com");
        String subject = "API TEST";
        com.sendgrid.Email to = new com.sendgrid.Email("diego@mensajerosurbanos.com");
        Content content = new Content("text/html", "1");
        Mail mail = new Mail(from, subject, to, content);
        mail.setTemplateId("14efa6ea-3454-4ba9-85b2-7d5a0e467288");
        MailSettings ms = new MailSettings();

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            //Response response = sg.api(request);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void sendSignUpTeacheMail(Teacher teacher) {
        com.sendgrid.Email from = new com.sendgrid.Email("tu-profe@operaciones.com");
        String subject = "Bienvenido Profesor";
        com.sendgrid.Email to = new com.sendgrid.Email("diego@mensajerosurbanos.com");
        Content content = new Content("text/html", "Para nosotros es un placer que ahora hagas parte del equipo de tu profe, por favor necesitamos que verifiques tu cuenta para ppoder seguir adelante con el proceso. Gracias");
        Mail mail = new Mail(from, subject, to, content);
        mail.personalization.get(0).addSubstitution("-tuprofemail-", "tu-profe@operaciones.com");
        mail.setTemplateId("14efa6ea-3454-4ba9-85b2-7d5a0e467288");

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            //Response response = sg.api(request);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void sendForgotPasswordTeacheMail(String nonce, String email) {
        com.sendgrid.Email from = new com.sendgrid.Email("tu-profe@operaciones.com");
        String subject = "Recuperar Contraseña";
        com.sendgrid.Email to = new com.sendgrid.Email(email);
        Content content = new Content("text/html", "Para recuperar tu contraseña por favor . Gracias");
        Mail mail = new Mail(from, subject, to, content);
        mail.personalization.get(0).addSubstitution("-tuprofemail-", "tu-profe@operaciones.com");
        mail.setTemplateId("8a998e7d-efd6-4001-8116-94f703014e3c");

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            //Response response = sg.api(request);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
