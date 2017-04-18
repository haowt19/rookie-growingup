package com.java.thread.wait;

public class Producer implements Runnable {

	private Share shared;
	
	private int number;
	
	public Producer(Share s, int number) {
		this.shared = s;
		this.number = number;
	}
	
	@Override
	public void run() {
		for(int i=0;i<10;i++) {
			shared.put(i);
			System.out.println("������"+this.number+" ���������Ϊ:"+i);
			 try{
				    Thread.currentThread().sleep((int)(Math.random()*100));
			   }catch(InterruptedException e){}
		}

	}

}
