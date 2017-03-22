package com.java.thread.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutor {

	/** 核心池大小，保持工作的线程的最小数量
	 *     除非将allowCoreThreadTimeOut设置为TRUE，
	 *     否则不允许有超时设定
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
