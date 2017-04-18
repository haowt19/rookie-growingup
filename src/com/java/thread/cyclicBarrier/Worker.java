package com.java.thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable{

	private final String name;
	
	private final CyclicBarrier barrier;
	
	public Worker(String name, CyclicBarrier barrier) {
		this.name = name;
		this.barrier = barrier;
	}
	
	@Override
	public void run() {
		System.out.println("the worker "+this.name+" is working");
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		try {
			barrier.await();
			System.out.println("�ȴ���������ʼ������������");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
