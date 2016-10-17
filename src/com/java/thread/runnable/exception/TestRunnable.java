package com.java.thread.runnable.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestRunnable {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool(new ExceptionHandlerThreadFactory());
		
//		Thread.setDefaultUncaughtExceptionHandler(new TestExceptionHandler());
		
		exec.execute(new ExceptionCaughtRunnable());
	}
}
