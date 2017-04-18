package com.java.thread.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * ���ڲ����߳�����ִ��ʱ��϶̣�
 * �����̳߳ص��ȼ��п���ʹ������˳��ִ��
 * @author 001244
 *
 */
public class TestTask implements Runnable {

	private final CountDownLatch beginLatch;
	private final CountDownLatch endLatch;
	
	public TestTask(CountDownLatch beginLatch, CountDownLatch endLatch) {
		this.beginLatch = beginLatch;
		this.endLatch = endLatch;
	}
	
	@Override
	public void run() {
		
		beginLatch.countDown();
		try {
			System.out.println(Thread.currentThread().getId()+Thread.currentThread().getName()+"�ȴ�");
			beginLatch.await();
			TimeUnit.SECONDS.sleep(1);
			System.out.println(Thread.currentThread().getId()+Thread.currentThread().getName()+"��ʼִ���߳�");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		endLatch.countDown();
		System.out.println(Thread.currentThread().getId()+Thread.currentThread().getName()+"����ִ���߳�");
	}

}
