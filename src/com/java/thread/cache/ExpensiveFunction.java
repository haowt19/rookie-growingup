package com.java.thread.cache;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class ExpensiveFunction implements Computable<String, BigInteger> {

	@Override
	public BigInteger compute(String arg) throws InterruptedException  {
		
		System.out.println(Thread.currentThread().getName()+"�������ڼ���");
		TimeUnit.SECONDS.sleep(5);
		
		return new BigInteger(arg);
	}


}
