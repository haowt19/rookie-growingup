package com.java.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Boss implements Runnable {

	private final CountDownLatch downLatch;
	
	public Boss(CountDownLatch downLatch) {
		this.downLatch = downLatch;
	}
	
	@Override
	public void run() {
		System.out.println("老板正在等所有的工人干完活......");  
		try {
			this.downLatch.await(1,TimeUnit.SECONDS);
			System.out.println("工人活都干完了，老板开始检查了！");  
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
