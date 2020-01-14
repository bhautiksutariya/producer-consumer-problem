package com.producerconsumer;

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
		
		Thread consumerThread = new Thread(() -> {
			try {
				producerConsumer.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		producerThread.start();
		consumerThread.start();
		producerThread.join();
		
		
	}

}
