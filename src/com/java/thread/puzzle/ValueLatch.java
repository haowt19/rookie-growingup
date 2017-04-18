package com.java.thread.puzzle;

import java.util.concurrent.CountDownLatch;

/**
 * 判断是否有目标点到达路径，
 * 并提供目标点到达的点
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
