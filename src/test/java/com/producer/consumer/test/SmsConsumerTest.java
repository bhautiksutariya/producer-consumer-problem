package com.producer.consumer.test;

import com.producerconsumer.entity.Consumer;
import com.producerconsumer.entity.Producer;
import com.producerconsumer.entity.Queue;
import com.producerconsumer.notification.NotificationFactory;
import com.producerconsumer.notification.SmsNotification;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmsConsumerTest {

    Queue queue;
    Producer producer;
    Consumer consumer;

    public SmsConsumerTest() {
        queue = new Queue(5);
    }

    //Initialization
    @BeforeEach
    public void init() {
        producer = new Producer(queue);
        consumer = new Consumer(queue);
    }

    //Test cases for SMS

    @Test
    public void testSmsType() throws Exception {
        String data = getSms();
        assertEquals(getType(data),"sms");
    }

    @Test
    public void testSmsFactory() throws Exception {
        String data = getSms();
        assertTrue(NotificationFactory.getNotification(data) instanceof SmsNotification);
    }

    @Test
    public void testIsSmsQueueEmpty() throws Exception {
        Assertions.assertThrows(Exception.class,()->{ consumer.smsConsume(); });
    }

    @Test
    public void testSmsNumber() throws Exception {
        String data = getSms();
        SmsNotification smsNotification= (SmsNotification) NotificationFactory.getNotification(data);
        assertNotNull(smsNotification.getNumber());
    }

    @Test
    public void testSmsMessage() throws Exception {
        String data = getSms();
        SmsNotification smsNotification= (SmsNotification) NotificationFactory.getNotification(data);
        assertNotNull(smsNotification.getMessage());
    }

    @Test
    public void testSmsAPI() throws Exception {
        String data = getSms();
        SmsNotification smsNotification= (SmsNotification) NotificationFactory.getNotification(data);
        assertNotNull(smsNotification.getAPI());
    }

    // For sms type
    String getSms() throws Exception {
        if(queue.items.size()==0)
            produceData();
        for(int i=0;i<queue.capacity;i++)
        {
            if(getType(queue.items.get(0)).equals("sms"))
                return queue.items.removeFirst();
            queue.items.removeFirst();
        }
        return getSms();
    }

    //Common methods
    void produceData() throws Exception {
        for(int i=queue.items.size();i<queue.capacity;i++)
            producer.produce();
    }

    public String getType(String data) {
        Object obj= JSONValue.parse(data);
        JSONObject jsonObject = (JSONObject) obj;
        return (String)jsonObject.getOrDefault("type","");
    }

}
