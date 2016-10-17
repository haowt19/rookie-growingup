package com.java.thread.runnable.exception;

public class TestExceptionHandler implements Thread.UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		System.out.println(" 抓住线程为 "+t +"的线程抛出的异常：" + e);
	}
	
}
