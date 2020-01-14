package com.producerconsumer.entity;

public class Consumer {
	
	private Queue queue;
	
	public Consumer(Queue queue) {
		this.queue=queue;
	}
	
	public int consume() throws IndexOutOfBoundsException {
		if(queue.items.isEmpty())
			throw new IndexOutOfBoundsException("Queue is empty");
		int data = queue.items.removeFirst();
		System.out.println("Consumer consumed : " + data);
		return data;
	}
}
