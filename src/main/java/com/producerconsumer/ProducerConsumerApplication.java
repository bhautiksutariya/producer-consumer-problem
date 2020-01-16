package com.producerconsumer;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.producerconsumer.entity.ProducerConsumer;

@SpringBootApplication
public class ProducerConsumerApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProducerConsumerApplication.class, args);
		ProducerConsumer producerConsumer = new ProducerConsumer(5);
		Thread producerThread = new Thread(() -> {
			try {
				producerConsumer.produce();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		Thread smsConsumerThread = new Thread(() -> {
			try {
				producerConsumer.smsConsume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Thread emailConsumerThread = new Thread(() -> {
			try {
				producerConsumer.emailConsume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Thread pushConsumerThread = new Thread(() -> {
			try {
				producerConsumer.pushConsume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		producerThread.start();
		smsConsumerThread.start();
		emailConsumerThread.start();
		pushConsumerThread.start();
		producerThread.join();
	}

}
