package com.java.thread.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * 并发性能较差
 * @author 001244
 *
 * @param <A>
 * @param <V>
 */
public class Memorizer1<A, V> implements Computable<A, V>{
	
	private final Map<A, V> cache = new HashMap<A, V>();
	
	private final Computable<A, V> comp;
	
	public Memorizer1(Computable<A, V> c) {
		this.comp = c;
	}
	

	@Override
	public synchronized V compute(A arg) throws InterruptedException {
		V result = cache.get(arg);
		if (result == null) {
			result = comp.compute(arg);
			cache.put(arg, result);
		}
		return result;
	}

}
