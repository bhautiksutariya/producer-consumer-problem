package com.producerconsumer.notification;

public class EmailNotification extends Notification {

    private String email;

    private String subject;

    public EmailNotification(String API, String message, String email, String subject) {
        super(API, message);
        this.email = email;
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
