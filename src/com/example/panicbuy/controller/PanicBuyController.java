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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.panicbuy.callable.BusinessCallable;
import com.example.panicbuy.model.Order;
import com.example.panicbuy.util.RedisLock;

/**
 * 秒杀功能的思路
 * 问题1：额度的争抢   								答案：利用redis.decr方法串行请求
 * 问题2：产生超卖情况的修复			 		答案：利用DB和Redis缓存同步来修复
 * 问题3：临界值的判定								答案：修复时会获取数据库中的剩余可投标额度R，根据R=0来判断是否已结束，如已结束，执行结束操作B
 * 问题4：并发多个请求进入修复请求时，会多次触发结束操作B  答案：在修复中增加项目状态的判定，已截标的项目不再触发。
 * @author 001244
 *
 */
@Controller
@RequestMapping(value="/buy")
public class PanicBuyController{

	
	@Autowired
	private RedisTemplate redisTemplate;
	
	private volatile boolean triggered = false;
	
	@ResponseBody
	@RequestMapping(value="/orderHandler")
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//数据校验，用户账户余额不足、项目不是出于可投标状态下的请求都打回
		validationCheck(uid, money, prjId);
		//执行抢标处理
		return handlePurchase(prjId, uid, money, key);
	}
	
	/**
	 * 
	 * @param prjId
	 * @param uid
	 * @param money
	 * @param key
	 * @return
	 */
	public String handlePurchase(Long prjId, Long uid, BigDecimal money, String key) {
		String result = "";
		BigDecimal remaining = getRemainingAmountFromRedis(money.doubleValue(), key);
		if (remaining.compareTo(BigDecimal.ZERO) >= 0) {
			try {
				//update good set remaining = remaining - money where good_id = prjId and remaining > money;
				goodService.doOrder(uid, money);
				result = "投资成功";
			} catch (Exception ex) {
				return syncDBAmountToRedis(key, prjId);
			}
		} else {
			returnRemainingAmount(money.doubleValue(), key);
			result = "剩余可投金额不足，投资失败!";
		}
		return result;
	}
	
	
	/**
	 * 触发点是业务逻辑处理出错，或者乐观锁并发抢不到额度，
	 * 有可能触发不到
	 * 改进措施：将同步数据库到redis的操作改为定时任务来处理
	 * 定时任务有可能造成缓存数据不正确，在考虑其他有效措施
	 * @param key
	 * @param prjId
	 * @return
	 */
	private String syncDBAmountToRedis(String key, Long prjId) {
		String result = "可投资额度已用完，投标结束";
		RedisLock lock = new RedisLock(redisTemplate, key);
		try {
			if (lock.lock()) {
				if (!triggered) {
					BigDecimal dbRemaining = goodService.getRemainAmountFromDB(prjId);
					if (dbRemaining.compareTo(BigDecimal.ZERO) == 0) {
						doTriggerEndBidOperation(prjId);
						triggered = true;
					} else {
						result = "剩余可投金额不足，投资失败!";
						redisTemplate.boundValueOps(key).set(dbRemaining);
					}
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
			lock.unlock();
		}
		
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	private BigDecimal getRemainingAmountFromRedis(double money, String key) {
		//此处加锁控制保证原子性
		double remainingAmount = redisTemplate.boundValueOps(key).increment(money*(-1));
		if (remainingAmount >= 0) {
			return BigDecimal.valueOf(remainingAmount);
		} else {
			redisTemplate.boundValueOps(key).increment(money);
			return BigDecimal.valueOf(-1);
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



