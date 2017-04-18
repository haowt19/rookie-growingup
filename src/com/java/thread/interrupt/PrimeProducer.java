package com.java.thread.interrupt;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class PrimeProducer extends Thread{
	
	private final BlockingQueue<BigInteger> queue;
	
	public PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!Thread.currentThread().isInterrupted()) {
				queue.put(p.nextProbablePrime());
			}
		} catch (InterruptedException ex) {
			/* 允许线程退出 */
		}
	}
	
	
	public void cancel() {
		interrupt();
	}

}
