package com.java.thread.syncontainer;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class IteratorTask implements Runnable {
	
	private ConcurrentHashMap<String, String> map;
	
	private volatile boolean flag = false;
	
	public IteratorTask(ConcurrentHashMap<String, String> v) {
		this.map = v;
	}

	@Override
	public void run() {
		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, String> data = iterator.next();
			System.out.println("key="+data.getKey()+" value="+data.getValue());
		}

	}

}
