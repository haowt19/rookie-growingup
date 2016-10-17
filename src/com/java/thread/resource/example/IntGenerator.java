package com.java.thread.resource.example;

public abstract class IntGenerator {

	private volatile boolean canceled = false;
	
	public abstract int next();
	
	public void cancel() {
		canceled = true;
	}
	
	
	public boolean isCanceled() {
		return canceled;
	}
}
