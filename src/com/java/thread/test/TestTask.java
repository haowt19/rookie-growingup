package com.java.thread.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 用于测试线程任务，执行时间较短，
 * 利用线程池调度极有可能使多任务顺序化执行
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
			System.out.println(Thread.currentThread().getId()+Thread.currentThread().getName()+"等待");
			beginLatch.await();
			TimeUnit.SECONDS.sleep(1);
			System.out.println(Thread.currentThread().getId()+Thread.currentThread().getName()+"开始执行线程");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		endLatch.countDown();
		System.out.println(Thread.currentThread().getId()+Thread.currentThread().getName()+"结束执行线程");
	}

}
