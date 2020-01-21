package com.producerconsumer.test.entity;

import com.producerconsumer.entity.Consumer;
import com.producerconsumer.entity.Producer;
import com.producerconsumer.entity.Queue;
import com.producerconsumer.notification.SmsNotification;
import com.producerconsumer.notification.factory.NotificationFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.producerconsumer.test.utility.ProducerConsumerTestUtil.getType;
import static com.producerconsumer.test.utility.ProducerConsumerTestUtil.getTypeof;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String data = consumeSms();
        assertEquals(getType(data),"sms");
    }

    @Test
    public void testIsSmsQueueEmpty() throws Exception {
        Assertions.assertThrows(Exception.class,()->{ consumer.smsConsume(); });
    }

    @Test
    public void testSmsNumber() throws Exception {
        String data = consumeSms();
        SmsNotification smsNotification= (SmsNotification) NotificationFactory.getNotification(data);
        assertNotNull(smsNotification.getNumber());
    }

    @Test
    public void testSmsMessage() throws Exception {
        String data = consumeSms();
        SmsNotification smsNotification= (SmsNotification) NotificationFactory.getNotification(data);
        assertNotNull(smsNotification.getMessage());
    }

    @Test
    public void testSmsAPI() throws Exception {
        String data = consumeSms();
        SmsNotification smsNotification= (SmsNotification) NotificationFactory.getNotification(data);
        assertNotNull(smsNotification.getAPI());
    }

    // For sms type
    String consumeSms() throws Exception {
        getTypeof("sms",queue,producer);
        return consumer.smsConsume();
    }



}
