package com.java.thread.resource.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable{

	private IntGenerator generator;
	
	private final int id;
	
	public EvenChecker(IntGenerator generator, int id) {
		this.generator = generator;
		this.id = id;
	}
	
	@Override
	public void run() {
		while (!generator.isCanceled()) {
			int value = generator.next();
			if (value % 2 != 0) {
				System.out.println(value+" is not even!");
				generator.cancel();
			}
		}
	}

	
	public static void test(IntGenerator generator, int count) {
		ExecutorService exec = Executors.newFixedThreadPool(3);
		for (int i=0; i<count; i++) {
			exec.execute(new EvenChecker(generator, i));
		}
		exec.shutdown();
	}
}
