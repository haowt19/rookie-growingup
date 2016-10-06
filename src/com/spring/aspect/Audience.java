package com.spring.aspect;

import org.apache.log4j.Logger;

public class Audience {

	private static final Logger logger = Logger.getLogger(Audience.class);
	
	public Audience() {
		
	}
	
	
	public void takeSeats() {
		logger.info("the audience is taking their seats");
	}
	
	
	public void turnOffCellPhones() {
		logger.info("the audience is turning off their cellphones");
	}
	
	
	public void applaud() {
		logger.info("Clap,Clap.....");
	}
	
	
	public void demandRefund() {
		logger.info("something is wrong, the audience demand refund");
	}
	
}
