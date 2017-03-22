package com.java.thread.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutor {

	/** ���ĳش�С�����ֹ������̵߳���С����
	 *     ���ǽ�allowCoreThreadTimeOut����ΪTRUE��
	 *     ���������г�ʱ�趨
	 * */
	private volatile int corePoolSize;
	
	
	private volatile int maximumPoolSize;
	
	
	private static final RejectedExecutionHandler defaultHandler = 
			new AbortPolicy();
	
	
	
	public ThreadPoolExecutor(int corePoolSize,
								int maximumPoolSize,
								long keepAliveTime,
								TimeUnit unit,
								BlockingQueue<Runnable> workQueue,
								ThreadFactory threadFactory,
								RejectedExecutionHandler handler) {
		
	}
	
	
	
	
	
}
