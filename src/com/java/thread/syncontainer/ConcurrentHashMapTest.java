package com.java.thread.syncontainer;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentHashMapTest {

	public static void main(String[] args) {
		ExecutorService exec = Executors.newFixedThreadPool(2);
		Future<Integer> future = exec.submit(new WriteTask());
		
		try {
			Integer result = future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	
	
}


class ReadTask implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}


class WriteTask implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}