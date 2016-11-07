package com.java.thread.runnable.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

/**
 *  展示在run()方法抛出异常后，在main()方法中无法捕获，
 *  要想能够捕获异常，需要新加一个threadFactory来构建线程池。
 * @author 001244
 *
 */
public class ExceptionRunnable implements Runnable{

	private static Logger logger = Logger.getLogger(ExceptionRunnable.class);
	
	@Override
	public void run() {
		throw new RuntimeException();
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(5);
		try {
			exec.execute(new ExceptionRunnable());
		} catch(Exception ex) {
			logger.error("执行过程发生错误");
		}
		
		exec.shutdown();
	}

}
