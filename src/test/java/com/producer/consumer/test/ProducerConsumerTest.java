package com.producer.consumer.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.producerconsumer.entity.*;


@RunWith(JUnit4.class)
class ProducerConsumerTest {

	Queue queue;
	Producer producer;
	Consumer consumer;
	
	public ProducerConsumerTest() {
		queue = new Queue(5);
	}
	
	
	//Initialization
	@BeforeEach
	public void init() {
		producer = new Producer(queue);
		consumer = new Consumer(queue);
	}
	
	
	//Test first Value
	@Test
	public void testProducerFirstValue() throws Exception {
		int data = producer.produce();
		System.out.println(data);
		assertEquals(data, 1);
	}
	
	//Test producer third value
	@Test
	void testProducerThirdValue() throws Exception {
		int data = producer.produce();
		data = producer.produce();
		data = producer.produce();
		assertEquals(data, 3);
	}
	
	
	//Test producer exception
	@Test
	void testProducerQueueFullException() throws Exception {
		produceData();
		Assertions.assertThrows(Exception.class, ()-> producer.produce());
	}
	
	
	//Test after queue is empty
	@Test
	void testProducerSixthValue() throws Exception {
		produceData();
		consumer.consume();
		assertEquals(6, producer.produce());
	}
	
	//Test consumer first value
	@Test
	void testConsumerFirstValue() throws Exception {
		produceData();
		int data = consumer.consume();
		assertEquals(data, 1);
	}
	
	//Test consumer second value
	@Test
	void testConsumerSecondValue() throws Exception {
		produceData();
		int data = consumer.consume();
		data = consumer.consume();
		assertEquals(data, 2);
	}
	
	//Test non zero value
	@Test
	void testConsumerNonZeroValue() throws Exception {
		produceData();
		int data = consumer.consume();
		data = consumer.consume();
		assertNotEquals(data, 0);
	}
	
	//Test were queue has started filling
	@Test
	void testConsumerSixthValue() throws Exception {
		produceData();
		consumeData();
		produceData();
		assertEquals(6, consumer.consume());
	}
	
	//Test consumer exception
	@Test
	void testConsumerException() throws Exception {
		Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> consumer.consume());
	}
	
	//Queue empty exception
	@Test
	void testConsumerAfterFiveConsumptionException() throws Exception {
		produceData();
		consumeData();
		Assertions.assertThrows(IndexOutOfBoundsException.class, ()-> consumer.consume());
	}
	
	
	//common method for producing data
	void produceData() throws Exception {
		for(int i=0;i<5;i++)
			producer.produce();
	}
	
	//common method for consuming data
	void consumeData() throws Exception {
		for(int i=0;i<5;i++)
			consumer.consume();
	}

}
