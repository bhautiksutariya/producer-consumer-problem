package com.producerconsumer.entity;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
				String s=producer.produce();
				notifyAll();
			}
			Thread.sleep(200);
		}
	}
	
	public void smsConsume() throws Exception {
		while(true) {
			synchronized(this) {
				while(queue.items.size() == 0 || !getType(queue.items.get(0)).equals("sms")) {
					wait();
				}
				consumer.smsConsume();
				notifyAll();
			}
			Thread.sleep(4000);
		}
	}

	public void emailConsume() throws Exception {
		while(true) {
			synchronized(this) {
				while(queue.items.size() == 0 || !getType(queue.items.get(0)).equals("email"))
					wait();
				consumer.emailConsume();
				notifyAll();
			}
			Thread.sleep(4000);
		}
	}

	public void pushConsume() throws Exception {
		while(true) {
			synchronized(this) {
				while(queue.items.size() == 0 || !getType(queue.items.get(0)).equals("push"))
					wait();
				consumer.pushConsume();
				notifyAll();
			}
			Thread.sleep(4000);
		}
	}

	public String getType(String data) {
		Object obj= JSONValue.parse(data);
		JSONObject jsonObject = (JSONObject) obj;
		return (String)jsonObject.getOrDefault("type","");
	}

}
