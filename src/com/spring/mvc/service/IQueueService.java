package com.spring.mvc.service;

public interface IQueueService {

	/**
	 * 将消息插入目标队列
	 * @param queId
	 * @param destName 目标队列
	 * @param destType
	 * @param msgObj 消息体
	 * @param objType
	 * @param objId 
	 */
	public void msgInsert(String queId, String destName, String destType, final Object msgObj, String objType, String objId);
	
	
	
	/**
	 * 获取队列主键值
	 * @return
	 */
	public String getQueId();
	
}
