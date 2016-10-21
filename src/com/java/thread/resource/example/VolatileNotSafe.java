package com.java.thread.resource.example;

import java.util.concurrent.TimeUnit;
/**
 * 讲述volatile关键字的使用
 * 详细内容可以访问：http://www.cnblogs.com/aigongsi/archive/2012/04/01/2429166.html
 * @author 001244
 *
 */
public class VolatileNotSafe {
	private volatile static int count = 0;
	
	public synchronized static int increase() {
		
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ++count;
	}
	
	public synchronized static int getCount() {
		return count;
	}
	
	
	public static void main(String[] args) {
		for (int i=0; i< 1000; i++) {
			new Thread(new Runnable(){
				
				public void run() {
					System.out.println("此时加总的结果为："+ VolatileNotSafe.increase());
				}
				
			}).start();
		}
	}
	
}



