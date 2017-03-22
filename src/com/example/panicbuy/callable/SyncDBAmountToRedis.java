package com.example.panicbuy.callable;

import java.math.BigDecimal;

import org.springframework.data.redis.core.RedisTemplate;


/**
 * 生成一个守护线程，定时更新缓存中的额度
 * @author 001244
 *
 */
public class SyncDBAmountToRedis implements Runnable{
	
	private Long prjId;
	
	private String key;
	
	private RedisTemplate redisTemplate;
	
	private IFiPrjService fiPrjService;
	
	public SyncDBAmountToRedis() {
		
	}

	@Override
	public void run() {
		BigDecimal remainingAmount = fiPrjService.getRemainingAmountFromDB(prjId);
		redisTemplate.boundValueOps(key).set(remainingAmount);
	}

}
