package com.producer.consumer.test;

import com.producerconsumer.entity.Consumer;
import com.producerconsumer.entity.Producer;
import com.producerconsumer.entity.Queue;
import com.producerconsumer.notification.NotificationFactory;
import com.producerconsumer.notification.EmailNotification;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmailConsumerTest {

    Queue queue;
    Producer producer;
    Consumer consumer;

    public EmailConsumerTest() {
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
    public void testEmailType() throws Exception {
        String data = getEmail();
        assertEquals(getType(data),"email");
    }

    @Test
    public void testEmailFactory() throws Exception {
        String data = getEmail();
        assertTrue(NotificationFactory.getNotification(data) instanceof EmailNotification);
    }

    @Test
    public void testIsEmailQueueEmpty() throws Exception {
        Assertions.assertThrows(Exception.class,()->{ consumer.emailConsume(); });
    }

    @Test
    public void testEmailAddress() throws Exception {
        String data = getEmail();
        EmailNotification emailNotification= (EmailNotification) NotificationFactory.getNotification(data);
        assertNotNull(emailNotification.getEmail());
    }

    @Test
    public void testEmailSubject() throws Exception {
        String data = getEmail();
        EmailNotification emailNotification= (EmailNotification) NotificationFactory.getNotification(data);
        assertNotNull(emailNotification.getSubject());
    }

    @Test
    public void testEmailMessage() throws Exception {
        String data = getEmail();
        EmailNotification emailNotification= (EmailNotification) NotificationFactory.getNotification(data);
        assertNotNull(emailNotification.getMessage());
    }

    @Test
    public void testEmailAPI() throws Exception {
        String data = getEmail();
        EmailNotification emailNotification= (EmailNotification) NotificationFactory.getNotification(data);
        assertNotNull(emailNotification.getAPI());
    }

    // For email type
    String getEmail() throws Exception {
        if(queue.items.size()==0)
            produceData();
        for(int i=0;i<queue.capacity;i++)
        {
            if(getType(queue.items.get(0)).equals("email"))
                return queue.items.removeFirst();
            queue.items.removeFirst();
        }
        return getEmail();
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
