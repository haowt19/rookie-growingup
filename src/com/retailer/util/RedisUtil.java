package com.retailer.util;

import java.math.BigDecimal;

import org.springframework.data.redis.core.RedisTemplate;

public class RedisUtil {
	
	private static final String key = "";
	
	private static final RedisTemplate<String, Double> redisTemplate 
					= new RedisTemplate<String, Double>();

	/**
	 * ÅÐ¶Ï»º´æÖÐµÄ¶î¶ÈÊÇ·ñ×ã¹»
	 * @param money
	 * @return
	 */
	public static boolean isRemainingAmountEnough(BigDecimal money) {
		synchronized (RedisUtil.class) {
			Double remains = redisTemplate.boundValueOps(key).increment(money.doubleValue()*-1);
			if (remains > 0) {
				return true;
			} else {
				redisTemplate.boundValueOps(key).increment(money.doubleValue());
				return false;
			}
		}
	}
	
	
	public static void returnAmountToCache(BigDecimal money) {
		redisTemplate.boundValueOps(key).increment(money.doubleValue());
	}
	
}
