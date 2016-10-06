package com.spring.redis.dao;

import com.spring.redis.entity.User;

public interface IUserDao {

	public void save(User user);
	
	
	public void delete(String uid);
	
	
	public User get(String uid);
	
	
}
