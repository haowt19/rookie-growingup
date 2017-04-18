package com.java.thread.cache;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 解决了A的计算过程后来的请求再次计算A的情况
 * 存在同时计算同一个数值的情况
 * @author 001244
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer3<A, V> implements Computable<A, V> {

	private final Map<A, Future<V>> cache 
						= new ConcurrentHashMap<A, Future<V>>();
	
	private final Computable<A, V> c;
	
	
	public Memoizer3(Computable<A, V> c) {
		this.c = c;
	}
	
	@Override
	public V compute(final A arg) throws InterruptedException {
		Future<V> f = cache.get(arg);
		if (f == null) {
			Callable<V> eval = new Callable<V>() {
				@Override
				public V call() throws Exception {
					return c.compute(arg);
				}
			};
			FutureTask<V> ft = new FutureTask<V>(eval);
			f = ft;
			cache.put(arg, ft);
			ft.run();
		}
		try {
			return f.get();
		} catch (ExecutionException e) {
			throw e;
		}
	}

}
