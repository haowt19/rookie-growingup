package com.java.thread.cache;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 解决的问题
 * 1：并发性能
 * 2：重复计算
 * 3：同时计算同一个值
 * 4：缓存污染
 * @author 001244
 *
 * @param <A>
 * @param <V>
 */
public class Memoizer<A, V> implements Computable<A, V> {

	private final ConcurrentHashMap<A, Future<V>> cache
						= new ConcurrentHashMap<A, Future<V>>();
	
	private final Computable<A, V> c;
	
	
	public Memoizer(Computable<A, V> c) {
		this.c = c;
	}
	
	
	@Override
	public V compute(final A arg) {
		while (true) {
			Future<V> f = cache.get(arg);
			if (f == null) {
				Callable<V> eval = new Callable<V>() {
					@Override
					public V call() throws Exception {
						return c.compute(arg);
					}
				};
				FutureTask<V> ft = new FutureTask<V>(eval);
				f = cache.putIfAbsent(arg, ft);
				if (f == null) {
					System.out.println(Thread.currentThread().getName()+"发现缓存中原先没有数据,将数据植入缓存，开始计算任务");
					f = ft;
					ft.run();
				}
			}
			
			try {
				System.out.println(Thread.currentThread().getName()+"现在进入等待获取结果");
				BigInteger result = (BigInteger) f.get();
				System.out.println(Thread.currentThread().getName()+"获取结果为"+result);
				return (V) result;
			} catch (InterruptedException e) {
				Thread.interrupted();
				System.out.println(Thread.currentThread().getName()+"被中断");
				cache.remove(arg, f);
			} catch (CancellationException ex) {
				System.out.println(Thread.currentThread().getName()+"被取消");
				cache.remove(arg, f);
			} catch (ExecutionException e) {
				System.out.println(Thread.currentThread().getState().toString());
				System.out.println(Thread.currentThread().getName()+"执行异常");
				cache.remove(arg, f);
			} 
		}
	}

}
