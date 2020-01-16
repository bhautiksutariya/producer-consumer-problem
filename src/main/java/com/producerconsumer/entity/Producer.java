package com.producerconsumer.entity;

import java.util.ArrayList;

public class Producer {

	private Queue queue;
	ArrayList<String> data;
	
	public Producer(Queue queue) {
		this.queue = queue;
		data=new ArrayList<>();
		generateData();
	}

	public void generateData() {

		//Email notification
		data.add("{\"type\":\"email\", \"api\":\"email api\", \"email\":\"abc@gmail.com\", \"msg\":\"This is sample email message body\", \"subject\":" +
				"\"This is subject 1\"}");
		data.add("{\"type\":\"email\", \"api\":\"email api\", \"email\":\"test@gmail.com\", \"msg\":\"you can credit card\", \"subject\":" +
				"\"Bank of zz\"}");
		data.add("{\"type\":\"email\", \"api\":\"email api\", \"email\":\"bhautik@gmail.com\", \"msg\":\" click and win a bike\", \"subject\":" +
				"\"Lucky draw\"}");
		data.add("{\"type\":\"email\", \"api\":\"email api\", \"email\":\"jay@gmail.com\", \"msg\":\"This is sample email message 2\", \"subject\":" +
				"\"This is subject 2\"}");
		data.add("{\"type\":\"email\", \"api\":\"email api\", \"email\":\"sample@gmail.com\", \"msg\":\"This is sample email message 3\", \"subject\":" +
				"\"This is subject 3\"}");

		//Sms notification
		data.add("{\"type\":\"sms\", \"api\":\"sms api\", \"msg\":\"get 10% off on next ride\", \"number\":" +
				"\"7656434567\"}");
		data.add("{\"type\":\"sms\", \"api\":\"sms api\", \"msg\":\"float 15% off\", \"number\":" +
				"\"9876567896\"}");
		data.add("{\"type\":\"sms\", \"api\":\"sms api\", \"msg\":\"get 5% cashback\", \"number\":" +
				"\"7865478659\"}");
		data.add("{\"type\":\"sms\", \"api\":\"sms api\", \"msg\":\"download app\", \"number\":" +
				"\"8765567842\"}");
		data.add("{\"type\":\"sms\", \"api\":\"sms api\", \"msg\":\"visit it once\", \"number\":" +
				"\"5678904323\"}");

		//Push notification
		data.add("{\"type\":\"push\", \"api\":\"push api\", \"msg\":\"new video is added\", \"relatedTo\":" +
				"\"user\", \"image\":\"abc.jpg\"}");
		data.add("{\"type\":\"push\", \"api\":\"push api\", \"msg\":\"meet celebrity\", \"relatedTo\":" +
				"\"engagement\", \"image\":\"xyz.jpg\"}");
		data.add("{\"type\":\"push\", \"api\":\"push api\", \"msg\":\"new video added to series\", \"relatedTo\":" +
				"\"user\", \"image\":\"qqq.jpg\"}");
		data.add("{\"type\":\"push\", \"api\":\"push api\", \"msg\":\"float 15% off on subscription\", \"relatedTo\":" +
				"\"offer\", \"image\":\"offer.jpg\"}");
		data.add("{\"type\":\"push\", \"api\":\"push api\", \"msg\":\"access it by Rs. 1/mon\", \"relatedTo\":" +
				"\"offer\", \"image\":\"sale.jpg\"}");
	}

	public String produce() throws Exception {
		if(queue.items.size()==queue.capacity)
			throw new Exception("Queue is full");
		int random = (int)(Math.random()*((data.size()-2)));
		queue.items.add(data.get(random));
		System.out.println("Producer produced       = " + data.get(random)+"\n");
		return data.get(random);
	}

}

