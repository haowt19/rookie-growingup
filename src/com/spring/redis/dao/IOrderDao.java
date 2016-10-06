package com.spring.redis.dao;

import com.spring.redis.entity.PrjOrder;

public interface IOrderDao {

	/**
	 * 将订单加入队列
	 * @param order
	 */
	public void push(String key, Object order);
	
	
	/**
	 * 从队列中取出订单
	 */
	public Object pop(String key);
	
	
	/**
	 * 队列长队
	 * @param key
	 * @return
	 */
	public Long length(String key);
	
	
	/**
	 * 移除
	 * @param key
	 * @param index
	 * @param value
	 */
	public void remove(String key, long index, PrjOrder order);
	
	
	/**
	 * 设置值
	 * @param key
	 * @param index
	 * @param value
	 */
	public void set(String key, long index, PrjOrder order);
}
