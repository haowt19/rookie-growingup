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
		System.out.println("�ϰ����ڵ����еĹ��˸����......");  
		try {
			this.downLatch.await(1,TimeUnit.SECONDS);
			System.out.println("���˻�����ˣ��ϰ忪ʼ����ˣ�");  
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
