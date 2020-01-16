package com.producerconsumer.notification;

public class PushNotification extends Notification {

    private String type;

    private String imageUrl;

    public PushNotification(String API, String message, String type, String imageUrl) {
        super(API, message);
        this.type = type;
        this.imageUrl = imageUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
