package com.producerconsumer.test.entity;

import com.producerconsumer.entity.Consumer;
import com.producerconsumer.entity.Producer;
import com.producerconsumer.entity.Queue;
import com.producerconsumer.notification.PushNotification;
import com.producerconsumer.notification.factory.NotificationFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.producerconsumer.test.utility.ProducerConsumerTestUtil.getType;
import static com.producerconsumer.test.utility.ProducerConsumerTestUtil.getTypeof;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

    //Test cases for Push

    @Test
    public void testPushType() throws Exception {
        String data = consumePush();
        assertEquals(getType(data),"push");
    }

    @Test
    public void testIsPushQueueEmpty() throws Exception {
        Assertions.assertThrows(Exception.class,()->{ consumer.pushConsume(); });
    }

    @Test
    public void testPushRelatedTo() throws Exception {
        String data = consumePush();
        PushNotification pushNotification= (PushNotification) NotificationFactory.getNotification(data);
        assertNotNull(pushNotification.getType());
    }

    @Test
    public void testPushMessage() throws Exception {
        String data = consumePush();
        PushNotification pushNotification= (PushNotification) NotificationFactory.getNotification(data);
        assertNotNull(pushNotification.getMessage());
    }

    @Test
    public void testPushAPI() throws Exception {
        String data = consumePush();
        PushNotification pushNotification= (PushNotification) NotificationFactory.getNotification(data);
        assertNotNull(pushNotification.getAPI());
    }

    // For push type
    String consumePush() throws Exception {
        getTypeof("push",queue,producer);
        return consumer.pushConsume();
    }

}
