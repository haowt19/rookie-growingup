package com.spring.mvc.dao;

public interface IBaseDao<T> {

	public void save(T entity);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public T get(Long id);
}
