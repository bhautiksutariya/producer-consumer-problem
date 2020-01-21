package com.producerconsumer.notification;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PushNotification extends Notification {

    private String type;

    private String imageUrl;

    @Builder
    public PushNotification(String API, String message, String type, String imageUrl) {
        super(API, message);
        this.type = type;
        this.imageUrl = imageUrl;
    }

}
