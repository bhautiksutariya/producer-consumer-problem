package com.producerconsumer.notification;

public class SmsNotification extends Notification {

    private String number;

    public SmsNotification(String API, String message, String number) {
        super(API, message);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
