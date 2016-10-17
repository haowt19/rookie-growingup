package com.java.thread.runnable.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.apache.log4j.Logger;

public class ExceptionCaughtRunnable implements Runnable{

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		System.out.println(" run() by "+ t + " and exception handler is " + t.getUncaughtExceptionHandler());
		throw new RuntimeException();
	}
	
}

