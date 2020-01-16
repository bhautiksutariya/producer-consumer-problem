package com.producer.consumer.test;

import com.producerconsumer.entity.Consumer;
import com.producerconsumer.entity.Producer;
import com.producerconsumer.entity.Queue;
import com.producerconsumer.notification.NotificationFactory;
import com.producerconsumer.notification.PushNotification;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PushConsumerTest {

    Queue queue;
    Producer producer;
    Consumer consumer;

    public PushConsumerTest() {
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
    public void testPushType() throws Exception {
        String data = getPush();
        assertEquals(getType(data),"push");
    }

    @Test
    public void testPushFactory() throws Exception {
        String data = getPush();
        assertTrue(NotificationFactory.getNotification(data) instanceof PushNotification);
    }

    @Test
    public void testIsPushQueueEmpty() throws Exception {
        Assertions.assertThrows(Exception.class,()->{ consumer.pushConsume(); });
    }

    @Test
    public void testPushRelatedTo() throws Exception {
        String data = getPush();
        PushNotification pushNotification= (PushNotification) NotificationFactory.getNotification(data);
        assertNotNull(pushNotification.getType());
    }

    @Test
    public void testPushMessage() throws Exception {
        String data = getPush();
        PushNotification pushNotification= (PushNotification) NotificationFactory.getNotification(data);
        assertNotNull(pushNotification.getMessage());
    }

    @Test
    public void testPushAPI() throws Exception {
        String data = getPush();
        PushNotification pushNotification= (PushNotification) NotificationFactory.getNotification(data);
        assertNotNull(pushNotification.getAPI());
    }

    // For push type
    String getPush() throws Exception {
        if(queue.items.size()==0)
            produceData();
        for(int i=0;i<queue.capacity;i++)
        {
            if(getType(queue.items.get(0)).equals("push"))
                return queue.items.removeFirst();
            queue.items.removeFirst();
        }
        return getPush();
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
