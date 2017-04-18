package com.java.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(4);
		CountDownLatch latch = new CountDownLatch(3);
		
		try {
			boolean flag = latch.await(2,TimeUnit.SECONDS);
			System.out.println(flag);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Worker w1 = new Worker(latch, "a");
		Worker w2 = new Worker(latch, "b");
		Worker w3 = new Worker(latch, "c");
		
		Boss boss = new Boss(latch);
		
		Future t1 = exec.submit(w1);
		try {
			Object t = t1.get();
			System.out.println("======"+t);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Future t2 = exec.submit(w2);
		Future t3 = exec.submit(w3);
		Future t4 = exec.submit(boss);
		
		exec.shutdown();
	}
}
