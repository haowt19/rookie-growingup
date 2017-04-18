package com.java.thread.cache;

import java.math.BigInteger;
import java.util.concurrent.Callable;

public class FactorTask implements Callable<BigInteger>{
	
	private final String argument;
	
	private final Computable<String, BigInteger> cache;
	
	public FactorTask(String argument, Computable<String, BigInteger> cache) {
		this.argument = argument;
		this.cache = cache;
	}

	@Override
	public BigInteger call() {
		System.out.println(Thread.currentThread().getName()+"��ʽ�ֽ�����ʼִ��");
		BigInteger result = null;
		try {
			result = cache.compute(argument);
		} catch (InterruptedException e) {
			System.out.println("FactorTask���ж�"+Thread.currentThread().getName());
		}
		return result;
	}

}
