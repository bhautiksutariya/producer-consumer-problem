package com.producerconsumer.notification;



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class NotificationFactory {

    public static Notification getNotification(String payload) throws Exception {

        if(payload==null)
            throw new Exception("Payload not null");

        Object obj= new JSONParser().parse(payload);
        JSONObject jsonObject = (JSONObject) obj;

        String type=(String) jsonObject.get("type");

        if(type.equals("sms"))
            return new SmsNotification((String)jsonObject.get("api"),(String)jsonObject.get("msg"),
                    (String)jsonObject.get("number"));
        else if(type.equals("email"))
            return new EmailNotification((String)jsonObject.get("api"),(String)jsonObject.get("msg"),
                    (String)jsonObject.get("email"),(String)jsonObject.get("subject"));

        return new PushNotification((String)jsonObject.get("api"),(String)jsonObject.get("msg"),
                (String)jsonObject.get("relatedTo"),(String)jsonObject.getOrDefault("image",""));

    }

}
