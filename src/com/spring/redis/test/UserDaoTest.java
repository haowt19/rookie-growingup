package com.spring.redis.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.spring.redis.dao.IUserDao;
import com.spring.redis.dao.impl.UserDaoImpl;
import com.spring.redis.entity.User;
import com.spring.util.NumGenerator;

public class UserDaoTest {

	private IUserDao userDao;
	
	
	@Test
	public void test() {
		String path="WebRoot/WEB-INF/springConf/springmvc-redis.xml"; 
		ApplicationContext context = new FileSystemXmlApplicationContext(path);
		userDao = (IUserDao) context.getBean("userDao");
		
		String uid = "u123445";
		String address = "上海";
		User user = new User();
		user.setUid(uid);
		user.setAddress(address);
		userDao.save(user);
		
		user = userDao.get(uid);
		
		System.out.println("the first address is "+ user.getAddress());
		assertEquals(address, user.getAddress());
		
		String address2 = "北京";
		user.setAddress(address2);
		userDao.save(user);
		
		user = userDao.get(uid);
		
		System.out.println("the second address is "+ user.getAddress());
		assertEquals(address, user.getAddress());
		
		userDao.delete(uid);
		user = userDao.get(uid);
		
		assertNull(user);
	}
	
	
	
}
