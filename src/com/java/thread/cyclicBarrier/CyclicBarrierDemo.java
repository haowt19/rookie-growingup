package com.java.thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CyclicBarrierDemo {

	public static void main(String[] args) {
		
		CyclicBarrier barrier = new CyclicBarrier(2, new Boss());
		
		try {
			int count = barrier.await(2, TimeUnit.SECONDS);
			System.out.println(count);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Worker w1 = new Worker("a", barrier);
		Worker w2 = new Worker("b", barrier);
		
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(w1);
		exec.execute(w2);
		
		exec.shutdown();
	}
	
}
 	