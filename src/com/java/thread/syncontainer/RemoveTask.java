package com.java.thread.syncontainer;

import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class RemoveTask implements Runnable{

	private ConcurrentHashMap<String, String> map;

	private final String key;

	
	
	public RemoveTask(ConcurrentHashMap<String, String> map,String key) {
		this.map = map;
		this.key = key;
	}
	
	
	@Override
	public void run() {
		map.remove(key);
		System.out.println("remove"+key);
	}

}
