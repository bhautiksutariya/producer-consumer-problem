package com.producerconsumer.test.entity;

import com.producerconsumer.entity.Consumer;
import com.producerconsumer.entity.Producer;
import com.producerconsumer.entity.Queue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.producerconsumer.test.utility.ProducerConsumerTestUtil.produceData;

public class ProducerTest {

    Queue queue;
    Producer producer;
    Consumer consumer;

    public ProducerTest() {
        queue = new Queue(5);
    }

    //Initialization
    @BeforeEach
    public void init() {
        producer = new Producer(queue);
        consumer = new Consumer(queue);
    }

    // Test case when queue is full
    @Test
    public void testIsQueueFull() throws Exception {
        produceData(queue,producer);
        Assertions.assertThrows(Exception.class,()->{ producer.produce(); });
    }

}
