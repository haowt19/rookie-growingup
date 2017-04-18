package com.java.thread.interrupt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * ����һ�ּ򵥵�ȡ������(how��when��what)�������龰����
 * ��ʹ�����ȡ�����Ե��õ���һ����������ʱ����BlockingQueue.put��
 * ���ǿ�������һ�������ص����⡪�����������Զ�������ȡ����־�������Զ�����սᡣ
 * ��������ģʽ��Ҫ����--�����ж�
 * �ܹ������������Ҫ���ǿ��ǣ�
 * 1�������� PrimeGenerator  
 * 2������֧���ж� PrimeProducer 
 * 3��������֧���ж�
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
			System.out.println("�����̱߳���ϣ���ǰ�̵߳��ж�״̬Ϊ"+Thread.currentThread().isInterrupted());
		} finally {
			Thread.currentThread().interrupt();
		}
		while (!Thread.currentThread().isInterrupted()) {
			System.out.println("���û���жϰ�");
		}
	}

	
	public void cancel() {
		cancelled = true;
	}
	
	
	public synchronized List<BigInteger> get() {
		return new ArrayList<BigInteger>(primes);
	}
	
}
