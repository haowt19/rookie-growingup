package com.java.thread.deadlock;

import java.util.concurrent.TimeUnit;

public class LeftRightDeadLock {
	private final Object left = new Object();
	private final Object right = new Object();
	
	public void leftRight() {
		synchronized (left) {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (right) {
				System.out.println("�Ȼ�ȡ��������ٻ�ȡ�ұ���");
			}
		}
	}
	
	public void rightLeft() {
		synchronized (right) {
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (left) {
				System.out.println("�Ȼ�ȡ�ұ������ٻ�ȡ�����");
			}
		}
	}
	
	
	
	public static void main(String[] args ) {
		LeftRightDeadLock lock = new LeftRightDeadLock();
		LeftRightDeadLock right = new LeftRightDeadLock();
		
		new Thread(new LeftRightTask(lock, 1)).start();
		new Thread(new LeftRightTask(lock, 2)).start();
		
		
		
	}
}
