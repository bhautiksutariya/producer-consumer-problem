package com.producerconsumer;

import com.producerconsumer.process.ProducerConsumerProcess;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProducerConsumerApplication {

	private static int size;

	@Value("${queue.size}")
	public void setSize(int size) {
		this.size = size;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProducerConsumerApplication.class, args);

		ProducerConsumerProcess producerConsumer = new ProducerConsumerProcess(size);
		Thread producerThread = new Thread(() -> {
			try {
				while (true)
					producerConsumer.produce();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Thread smsConsumerThread = new Thread(() -> {
			try {
				while (true)
					producerConsumer.smsConsume();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Thread emailConsumerThread = new Thread(() -> {
			try {
				while (true)
					producerConsumer.emailConsume();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		Thread pushConsumerThread = new Thread(() -> {
			try {
				while (true)
					producerConsumer.pushConsume();
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
