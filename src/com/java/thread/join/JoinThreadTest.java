package com.java.thread.join;

import java.util.concurrent.TimeUnit;

public class JoinThreadTest implements Runnable{

	private String name;
	
	public JoinThreadTest(String name) {
		this.name = name;
	}
	

	@Override
	public void run() {
		
		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println("线程"+name+"正在执行");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Thread t1= new Thread(new JoinThreadTest("t1"));
		Thread t2 = new Thread(new JoinThreadTest("t2"));
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("主线程执行完毕");
	}
}
