package com.java.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable{

	private final String name;
	
	private CountDownLatch downLatch;
	
	
	public Worker(CountDownLatch downLatch, String name) {
		this.name = name;
		this.downLatch = downLatch;
	}
	
	
	@Override
	public void run() {
		System.out.println("the worker "+this.name+" is working");
		try {
			TimeUnit.MILLISECONDS.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		this.downLatch.countDown();
	}

}
