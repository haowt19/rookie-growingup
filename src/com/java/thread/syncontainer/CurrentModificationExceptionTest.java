package com.java.thread.syncontainer;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class CurrentModificationExceptionTest {

	public static void main(String[] args) {
//		Vector<String> vector = new Vector<String>();
//		for (int i=0;i<100;i++) {
//			vector.add(i+"");
//		}
//		
//		new Thread(new IteratorTask(vector)).start();
//		new Thread(new RemoveTask(vector,98)).start();
		
		
		ConcurrentHashMap<String, String> cMap = new ConcurrentHashMap<String, String>();
		for (int i=0;i<100;i++) {
			cMap.put(""+i, i+"");
		}
		new Thread(new IteratorTask(cMap)).start();
//		try {
//			TimeUnit.SECONDS.sleep((long) 0.1);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		new Thread(new RemoveTask(cMap,1+"")).start();
		
	}
	
}
