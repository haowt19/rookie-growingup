package com.java.thread.cache;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * ���������
 * 1����������
 * 2���ظ�����
 * 3��ͬʱ����ͬһ��ֵ
 * 4��������Ⱦ
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
					System.out.println(Thread.currentThread().getName()+"���ֻ�����ԭ��û������,������ֲ�뻺�棬��ʼ��������");
					f = ft;
					ft.run();
				}
			}
			
			try {
				System.out.println(Thread.currentThread().getName()+"���ڽ���ȴ���ȡ���");
				BigInteger result = (BigInteger) f.get();
				System.out.println(Thread.currentThread().getName()+"��ȡ���Ϊ"+result);
				return (V) result;
			} catch (InterruptedException e) {
				Thread.interrupted();
				System.out.println(Thread.currentThread().getName()+"���ж�");
				cache.remove(arg, f);
			} catch (CancellationException ex) {
				System.out.println(Thread.currentThread().getName()+"��ȡ��");
				cache.remove(arg, f);
			} catch (ExecutionException e) {
				System.out.println(Thread.currentThread().getState().toString());
				System.out.println(Thread.currentThread().getName()+"ִ���쳣");
				cache.remove(arg, f);
			} 
		}
	}

}
