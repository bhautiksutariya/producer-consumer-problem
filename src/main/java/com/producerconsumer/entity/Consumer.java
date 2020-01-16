package com.producerconsumer.entity;

import com.producerconsumer.notification.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Consumer {
	
	private Queue queue;
	
	public Consumer(Queue queue) {
		this.queue=queue;
	}
	
	public String smsConsume() throws Exception {
		if(queue.items.isEmpty())
			throw new Exception("Queue is empty");
		String payload = queue.items.removeFirst();
		SmsNotification notification = (SmsNotification) NotificationFactory.getNotification(payload);
		System.out.println("Sms Consumer consumed   = { API : " +notification.getAPI()+ ", Message : " + notification.getMessage() + ", Number :"+ notification.getNumber() + "}\n");
		return payload;
	}

	public String emailConsume() throws Exception {
		if(queue.items.isEmpty())
			throw new Exception("Queue is empty");
		String payload = queue.items.removeFirst();
		EmailNotification notification = (EmailNotification) NotificationFactory.getNotification(payload);
		System.out.println("Email Consumer consumed = { API : " +notification.getAPI()+ ", Message : " + notification.getMessage() + ", Subject :"+ notification.getSubject() + "}\n");
		return payload;
	}

	public String pushConsume() throws Exception {
		if(queue.items.isEmpty())
			throw new Exception("Queue is empty");
		String payload = queue.items.removeFirst();
		PushNotification notification = (PushNotification) NotificationFactory.getNotification(payload);
		System.out.println("Push Consumer consumed  = { API : " + notification.getAPI() + ", Message : " + notification.getMessage() + ", Image :"+ notification.getImageUrl() +
				", Type :" + notification.getType() + "}\n");
		return payload;
	}
}
