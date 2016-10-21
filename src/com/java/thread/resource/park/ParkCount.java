package com.java.thread.resource.park;

public class ParkCount {
	private static int sum = 0;
	
	private static volatile boolean cancel = false;
	
	public synchronized static int getSum() {
		return ParkCount.sum;
	}
	
	
	public synchronized static int addSum() {
		return ++ParkCount.sum; 
	}


	public static boolean isCancel() {
		return cancel;
	}


	public static void setCancel(boolean cancel) {
		ParkCount.cancel = cancel;
	}
	
	
}
