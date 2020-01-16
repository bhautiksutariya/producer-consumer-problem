package com.producerconsumer.entity;

import java.util.LinkedList;

public class Queue {
	
	public LinkedList<String> items;
	public int capacity;
	
	public Queue(int capacity) {
		this.capacity=capacity;
		items=new LinkedList<>();
	}

}
