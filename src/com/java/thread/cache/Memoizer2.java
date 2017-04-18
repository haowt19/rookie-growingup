package com.java.thread.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 解决了并发性能问题
 * 存在在计算A的过程中，后面的请求再次计算过程A
 * @author 001244
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer2<A, V> implements Computable<A, V> {

	private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
	
	private final Computable<A, V> comp;
	
	public Memoizer2(Computable<A, V> c) {
		this.comp = c;
	}
	
	@Override
	public V compute(A arg) throws InterruptedException {
		V result = cache.get(arg);
		if (result == null) {
			result = comp.compute(arg);
			cache.put(arg, result);
		}
		
		return result;
	}

}
