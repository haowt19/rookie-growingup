package com.java.thread.wait;

public class WaitTest {

	public static void main(String[] args) {
		Share s=new Share();
		  Producer p=new Producer(s,1);
		  Consumer c=new Consumer(s,1);
		  new Thread(p).start();
		  new Thread(c).start();

	}

}
