package com.producerconsumer.entity;

public class ProducerConsumer {
	
	private Queue queue;
	private Producer producer;
	private Consumer consumer;
	
	public ProducerConsumer(int capacity) {
		queue = new Queue(capacity);
		producer = new Producer(queue);
		consumer = new Consumer(queue);
	}
	
	public void produce() throws Exception {
		while(true) {
			synchronized(this) {
				while(queue.items.size() == queue.capacity)
					wait();
				producer.produce();
				notify();
			}
			Thread.sleep(400);
		}
	}
	
	public void consume() throws InterruptedException {
		while(true) {
			synchronized(this) {
				while(queue.items.size() == 0)
					wait();
				consumer.consume();
				notify();
			}
			Thread.sleep(1000);
		}
	}

}
