package com.spring.redis.dao.impl;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.spring.redis.dao.IOrderDao;
import com.spring.redis.entity.PrjOrder;

@Repository("orderDao")
public class OrderDaoImpl implements IOrderDao{

	private RedisTemplate<String, Object> redisTemplate;
	
	public void push(String key, Object order) {
		redisTemplate.opsForList().leftPush(key, order);
	}

	@Override
	public Object pop(String key) {
		return (Object)redisTemplate.opsForList().rightPop(key);
	}

	@Override
	public Long length(String key) {
		return redisTemplate.opsForList().size(key);
	}

	@Override
	public void remove(String key, long index, PrjOrder order) {
		redisTemplate.opsForList().remove(key, index, order);
	}

	@Override
	public void set(String key, long index, PrjOrder order) {
		redisTemplate.opsForList().set(key, index, order);
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	
	
}
