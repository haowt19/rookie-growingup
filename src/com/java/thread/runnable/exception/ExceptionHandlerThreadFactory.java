package com.java.thread.runnable.exception;

import java.util.concurrent.ThreadFactory;

public class ExceptionHandlerThreadFactory implements ThreadFactory {

	@Override
	public Thread newThread(Runnable runnable) {
		Thread thread = new Thread(runnable);
		thread.setUncaughtExceptionHandler(new TestExceptionHandler());
		System.out.println("创建线程"+thread+"且线程的对于未捕获到的异常的处理方法为："+thread.getUncaughtExceptionHandler());
		return thread;
	}
	
}
