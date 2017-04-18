package com.java.thread.interrupt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 这是一种简单的取消策略(how、when、what)，适用情景有限
 * 当使用这个取消策略调用的是一个阻塞方法时，如BlockingQueue.put，
 * 我们可能遇见一个更严重的问题——任务可能永远都不检查取消标志，因此永远不会终结。
 * 所以这种模式需要升级--利用中断
 * 总共有三种情况需要我们考虑：
 * 1：非阻塞 PrimeGenerator  
 * 2：阻塞支持中断 PrimeProducer 
 * 3：阻塞不支持中断
 * @author 001244
 *
 */
public class PrimeGenerator implements Runnable {

	private final List<BigInteger> primes = new ArrayList<BigInteger>();
	
	private volatile boolean cancelled;
	
	@Override
	public void run() {
//		BigInteger p = BigInteger.ONE;
//		while(!cancelled) {
//			p = p.nextProbablePrime();
//			synchronized (this) {
//				primes.add(p);
//			}
//		}
		
		try {
			TimeUnit.SECONDS.sleep(8);
		} catch (InterruptedException e) {
			System.out.println("任务线程被打断，当前线程的中断状态为"+Thread.currentThread().isInterrupted());
		} finally {
			Thread.currentThread().interrupt();
		}
		while (!Thread.currentThread().isInterrupted()) {
			System.out.println("真的没有中断啊");
		}
	}

	
	public void cancel() {
		cancelled = true;
	}
	
	
	public synchronized List<BigInteger> get() {
		return new ArrayList<BigInteger>(primes);
	}
	
}
