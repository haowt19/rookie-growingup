package com.java.thread.deadlock;

public class LeftRightTask implements Runnable{
	
	private final LeftRightDeadLock deadLock;
	
	private final int leftRightFlag;

	public LeftRightTask(LeftRightDeadLock lock, int flag) {
		this.deadLock = lock;
		this.leftRightFlag = flag;
	}
	
	
	@Override
	public void run() {
		if (leftRightFlag == 1) {
			deadLock.leftRight();
		} else {
			deadLock.rightLeft();
		}
	}

}
