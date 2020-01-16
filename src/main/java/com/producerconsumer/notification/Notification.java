package com.producerconsumer.notification;

public class Notification {

    private String API;

    private String message;

    public Notification(String API, String message) {
        this.API = API;
        this.message = message;
    }

    public String getAPI() {
        return API;
    }

    public void setAPI(String API) {
        this.API = API;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
