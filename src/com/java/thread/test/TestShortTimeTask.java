package com.java.thread.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestShortTimeTask {
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		
		CountDownLatch beginLatch = new CountDownLatch(20);
		CountDownLatch endLatch = new CountDownLatch(20);
		
		for (int i=0;i<20;i++) {
			exec.execute(new TestTask(beginLatch, endLatch));
		}
		
		try {
			endLatch.await(1, TimeUnit.MILLISECONDS);
			System.out.println("主线程执行结束");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exec.shutdown();
		
	}
	

}
