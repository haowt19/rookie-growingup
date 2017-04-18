package com.java.thread.cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CacheDemo {
	

	public static void main(String[] args) {
		
//		try {
//			System.setOut(new PrintStream(new File("E:\\test.log")));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		 Computable<String, BigInteger> c = new ExpensiveFunction();
		
		 Computable<String, BigInteger> cache = new Memoizer<>(c);
		 
		ExecutorService exec = Executors.newCachedThreadPool();
		
		FactorTask t1 = new FactorTask("30", cache); 
		FactorTask t0 = new FactorTask("30", cache);
		
		Future<BigInteger> result0 = exec.submit(t0);
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e1) {
			System.out.println("0被打断的线程"+Thread.currentThread().getName());
		}
		Future<BigInteger> result1 = exec.submit(t1); 
		
		try {
			TimeUnit.SECONDS.sleep(1);
			result0.cancel(true);
		} catch (InterruptedException e1) {
			System.out.println("1被打断的线程"+Thread.currentThread().getName());
		}
		
		try {
			System.out.println(Thread.currentThread().getName() +"获取到的结果为:"+result0.get());
			System.out.println(Thread.currentThread().getName() +"获取到的结果为:"+result1.get());
		} catch (InterruptedException e) {
			System.out.println("2被打断的线程"+Thread.currentThread().getName());
		} catch (ExecutionException e) {
			System.out.println("3执行异常的线程"+Thread.currentThread().getName());
		} catch (CancellationException ex) {
			System.out.println("4被取消的线程"+Thread.currentThread().getName());
		}
		
		exec.shutdown();
	}

}
