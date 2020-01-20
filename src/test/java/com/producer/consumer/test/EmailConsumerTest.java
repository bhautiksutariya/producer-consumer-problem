package com.producer.consumer.test;

import com.producerconsumer.entity.Consumer;
import com.producerconsumer.entity.Producer;
import com.producerconsumer.entity.Queue;
import com.producerconsumer.notification.EmailNotification;
import com.producerconsumer.notification.NotificationFactory;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    //Test cases for Email

    @Test
    public void testEmailType() throws Exception {
        String data = consumeEmail();
        assertEquals(getType(data),"email");
    }

    @Test
    public void testIsEmailQueueEmpty() throws Exception {
        Assertions.assertThrows(Exception.class,()->{ consumer.emailConsume(); });
    }

    @Test
    public void testEmailAddress() throws Exception {
        String data = consumeEmail();
        EmailNotification emailNotification= (EmailNotification) NotificationFactory.getNotification(data);
        assertNotNull(emailNotification.getEmail());
    }

    @Test
    public void testEmailSubject() throws Exception {
        String data = consumeEmail();
        EmailNotification emailNotification= (EmailNotification) NotificationFactory.getNotification(data);
        assertNotNull(emailNotification.getSubject());
    }

    @Test
    public void testEmailMessage() throws Exception {
        String data = consumeEmail();
        EmailNotification emailNotification= (EmailNotification) NotificationFactory.getNotification(data);
        assertNotNull(emailNotification.getMessage());
    }

    @Test
    public void testEmailAPI() throws Exception {
        String data = consumeEmail();
        EmailNotification emailNotification= (EmailNotification) NotificationFactory.getNotification(data);
        assertNotNull(emailNotification.getAPI());
    }

    // For email type
    void getEmail() throws Exception {
        if(queue.items.size()==0)
            produceData();
        for(int i=0;i<queue.capacity;i++)
        {
            if(getType(queue.items.get(0)).equals("email"))
                return;
            queue.items.removeFirst();
        }
        getEmail();
    }

    String consumeEmail() throws Exception {
        getEmail();
        return consumer.emailConsume();
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
