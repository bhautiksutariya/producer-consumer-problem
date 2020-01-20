package com.producer.consumer.test;

import com.producerconsumer.notification.EmailNotification;
import com.producerconsumer.notification.NotificationFactory;
import com.producerconsumer.notification.PushNotification;
import com.producerconsumer.notification.SmsNotification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FactoryTest {

    @Test
    public void testEmptyPayload() throws Exception {
        Assertions.assertThrows(Exception.class,()->{ NotificationFactory.getNotification(null); });
    }

    @Test
    public void testEmailFactory() throws Exception {
        String data = "{\"type\":\"email\", \"api\":\"email api\", \"email\":\"abc@gmail.com\", \"msg\":\"This is sample email message body\", \"subject\":" +
                "\"This is subject 1\"}";
        assertTrue(NotificationFactory.getNotification(data) instanceof EmailNotification);
    }

    @Test
    public void testPushFactory() throws Exception {
        String data = "{\"type\":\"push\", \"api\":\"push api\", \"msg\":\"new video is added\", \"relatedTo\":" +
                "\"personalization\", \"image\":\"abc.jpg\"}";
        assertTrue(NotificationFactory.getNotification(data) instanceof PushNotification);
    }

    @Test
    public void testSmsFactory() throws Exception {
        String data = "{\"type\":\"sms\", \"api\":\"sms api\", \"msg\":\"get 10% off on next ride\", \"number\":" +
                "\"7656434567\"}";
        assertTrue(NotificationFactory.getNotification(data) instanceof SmsNotification);
    }

}
