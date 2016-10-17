package com.spring.mvc.dao.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.spring.mvc.dao.IBaseDao;

public class BaseDaoImpl<T> extends JdbcDaoSupport implements IBaseDao<T>{

	
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public T get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
