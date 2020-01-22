package com.producerconsumer.test.entity;

import com.producerconsumer.entity.Consumer;
import com.producerconsumer.entity.Producer;
import com.producerconsumer.entity.Queue;
import com.producerconsumer.notification.EmailNotification;
import com.producerconsumer.notification.factory.NotificationFactory;
import com.producerconsumer.test.utility.ProducerConsumerTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import static com.producerconsumer.test.utility.ProducerConsumerTestUtil.getType;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    //For email type
    String consumeEmail() throws Exception {
        ProducerConsumerTestUtil.getTypeof("email",queue,producer);
        return consumer.emailConsume();
    }


}
