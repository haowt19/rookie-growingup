package com.java.thread.resource.park;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class GateStatistic implements Runnable{
	
	private Logger logger = Logger.getLogger(getClass());
	
	/** 用于统计这个门进入的游客的数量 */
	private int count;
	
	/** 用于标识门的主键 */
	private final int gateId;
	
	public GateStatistic(int gateId) {
		this.gateId = gateId;
	}

	@Override
	public void run() {
		
		while (!ParkCount.isCancel()) {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (this) {
				count++;
			}
			
			logger.info("公园第"+gateId+"个门进入游客数量为："+getValue()+"进入公园的总人数为："+ParkCount.addSum());
		}
	}
	
	
	public synchronized int getValue() {
		return count;
	}

}
