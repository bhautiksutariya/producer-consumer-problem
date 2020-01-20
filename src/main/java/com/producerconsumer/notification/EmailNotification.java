package com.producerconsumer.notification;

import lombok.Builder;
import lombok.Data;

@Data
public class EmailNotification extends Notification {

    private String email;

    private String subject;

    @Builder
    public EmailNotification(String API, String message, String email, String subject) {
        super(API, message);
        this.email = email;
        this.subject = subject;
    }

}
