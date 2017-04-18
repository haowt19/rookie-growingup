package com.java.thread.puzzle;

import java.util.concurrent.CountDownLatch;

/**
 * �ж��Ƿ���Ŀ��㵽��·����
 * ���ṩĿ��㵽��ĵ�
 * @author 001244
 *
 * @param <T>
 */
public class ValueLatch<T> {
	
	private T value = null;
	
	private final CountDownLatch done = new CountDownLatch(1);
	
	
	public boolean isSet() {
		return (done.getCount() == 0 );
	}


	public T getValue() throws InterruptedException {
		done.await();
		synchronized (this) {
			return value;
		}
	}


	public synchronized void setValue(T newValue) {
		if ( !isSet() ) {
			this.value = value;
			done.countDown();
		}
	}
	
	
	

}
