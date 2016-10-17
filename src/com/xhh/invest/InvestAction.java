package com.xhh.invest;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.xhh.invest.entity.FiPrjOrder;
import com.xhh.invest.service.IInvestService;

public class InvestAction {
	
	private static final Logger logger = Logger.getLogger(InvestAction.class);

	@Resource
	private IInvestService investService;
	
	@Resource
	private RedisTemplate redisTemplate;
	
	@Test
	public void test() {
		String path="src/com/spring/redis/springmvc-redis.xml"; 
		ApplicationContext context = new FileSystemXmlApplicationContext(path);
		redisTemplate = (RedisTemplate)context.getBean("redisTemplate");
		
		Long prjId = 1123L;
		BigDecimal money = BigDecimal.valueOf(200);
		invest(prjId,money);
	}
	
	
	public void invest(Long prjId, BigDecimal money) {
		String remainAmount = (String)redisTemplate.boundValueOps("remainingAmount"+prjId).get();
		BigDecimal remainingAmount = BigDecimal.valueOf(Long.parseLong(remainAmount));
		if (remainingAmount.doubleValue() <= 0) {
			logger.info("项目剩余额度为零，已无法接收订单");
			return;
		}
		
		if (remainingAmount.compareTo(money) < 0) {
			logger.info("项目剩余额度小于订单金额 ！");
			return;
		}
		
		redisTemplate.boundValueOps("remainingAmount"+prjId).set(remainingAmount.subtract(money));
		
	}
}
