package com.producerconsumer.notification;

import lombok.Builder;
import lombok.Data;

@Data
public class SmsNotification extends Notification {

    private String number;

    @Builder
    public SmsNotification(String API, String message, String number) {
        super(API, message);
        this.number = number;
    }

}
