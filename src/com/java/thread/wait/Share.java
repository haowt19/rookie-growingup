package com.java.thread.wait;

public class Share {

	private int contents;
	
	private volatile boolean available = false;
	
	public synchronized int get() {
		while (available == false) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		System.out.println("获取到数据"+contents);
		available = false;
		notifyAll();
		return contents;
	}
	
	public synchronized void put(int value) {
		while (available == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		available = true;
//		System.out.println("生产了一个数据"+contents);
		notifyAll();
		contents = value;
	}
}
