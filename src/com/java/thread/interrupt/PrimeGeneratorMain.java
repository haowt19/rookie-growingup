package com.java.thread.interrupt;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PrimeGeneratorMain {

	public List<BigInteger> oneSecondOfPrimes() throws InterruptedException{
		PrimeGenerator generator = new PrimeGenerator();
		new Thread(generator).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} finally {
			generator.cancel();
		}
		
		return generator.get();
	}
	
	
	public static  void main(String[] args) {
		PrimeGenerator generator = new PrimeGenerator();
		Thread t = new Thread(generator);
		t.start();
		t.interrupt();
		System.out.println("t.�ж�״̬Ϊ"+t.isInterrupted());
	}
	
}
