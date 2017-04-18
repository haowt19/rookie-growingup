package com.java.thread.wait;

public class Consumer implements Runnable {

	private Share shared;
	 private int number;
	 
	 public Consumer(Share s,int number){
	  shared=s;
	  this.number=number;
	 }
	 
	 public void run(){
	  int value=0;
	  for(int i=0;i<10;i++){
	   value=shared.get();
	   System.out.println("消费者"+this.number+" 得到的数据为:"+value);
	  }
	 }

}
