package com.producerconsumer.entity;

public class Producer {

	private Queue queue;
	int value;
	
	public Producer(Queue queue) {
		this.queue = queue;
		value=0;
	}
	
	public int produce() throws Exception {
		if(queue.items.size()==queue.capacity)
			throw new Exception("Queue is full");
		queue.items.add(++value);
		System.out.println("Producer produced : " + value);
		return value;
	}
}

