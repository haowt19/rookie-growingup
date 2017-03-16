package com.example.panicbuy.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.example.panicbuy.callable.BusinessCallable;
import com.example.panicbuy.model.Order;
import com.example.panicbuy.util.RedisLock;

/**
 * 秒杀功能的思路
 * @author 001244
 *
 */
public class PanicBuyController implements Controller{

	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Long uid = Long.parseLong(request.getParameter("uid"));
		BigDecimal money = BigDecimal.valueOf(Double.parseDouble(request.getParameter("money")));
		String key = "remainingAmount";
		String resultInfo = "";
		
		//校验订单数据合规性
		
		//抢夺项目剩余可投金额
		BigDecimal resultMoney = getRemainingAmountWithoutLock(money.doubleValue(), key);
		if (resultMoney.compareTo(new BigDecimal(0)) > 0 ) {
			try {
				//模拟业务处理时间
				TimeUnit.MILLISECONDS.sleep(500);
				
				//生成订单信息
				
				//冻结投资人账户相应资金，采用乐观锁进行账户的操作
				//如果冻结失败，返回用户投资失败
				
				//生成账户相关流水信息
				
				resultInfo = "投标成功!";
			} catch (Exception ex) {
				resultInfo= "业务逻辑处理失败，抢标失败";
				returnRemainingAmount(money.doubleValue(), key);
			}
		} else {
			resultInfo= "项目标的已经被抢光!";
		}
		return new ModelAndView((String) resultInfo);
	}
	
	@SuppressWarnings("unchecked")
	private BigDecimal getRemainingAmountWithoutLock(double money, String key) {
		double remainingAmount = redisTemplate.boundValueOps(key).increment(money*(-1));
		if (remainingAmount >= 0) {
			return BigDecimal.valueOf(money);
		} else {
			return BigDecimal.valueOf(0);
		}
	}
	
	/**
	 * 业务处理失败，退还占用的剩余可投标额度
	 * @param money
	 * @param key
	 */
	private void returnRemainingAmount(double money, String key) {
		redisTemplate.boundValueOps(key).increment(money);
	}
	
}



