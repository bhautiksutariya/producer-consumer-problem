package com.producerconsumer.notification.factory;

import com.producerconsumer.notification.EmailNotification;
import com.producerconsumer.notification.Notification;
import com.producerconsumer.notification.PushNotification;
import com.producerconsumer.notification.SmsNotification;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class NotificationFactory {

    public static Notification getNotification(String payload) throws Exception {

        if(payload==null)
            throw new Exception("Payload not null");

        Object obj= new JSONParser().parse(payload);
        JSONObject jsonObject = (JSONObject) obj;

        String type=(String) jsonObject.get("type");

        switch (type)
        {
            case "sms" :
                return SmsNotification.builder().API((String)jsonObject.get("api")).message((String)jsonObject.get("msg")).
                        number((String)jsonObject.get("number")).build();
            case "email" :
                return EmailNotification.builder().API((String)jsonObject.get("api")).message((String)jsonObject.get("msg")).
                        email((String)jsonObject.get("email")).subject((String)jsonObject.get("subject")).build();
            default :
                return PushNotification.builder().API((String)jsonObject.get("api")).message((String)jsonObject.get("msg")).
                        type((String)jsonObject.get("relatedTo")).imageUrl((String)jsonObject.getOrDefault("image","")).build();
        }

    }

}