package com.tuprofe.api.entities;

/**
 *
 * @author diego
 */
public class EmailSQS {

    private String to;
    private String subject;
    private String body;

    public EmailSQS() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
