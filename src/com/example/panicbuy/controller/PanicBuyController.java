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
 * ��ɱ���ܵ�˼·
 * ����1����ȵ�����   								�𰸣�����redis.decr������������
 * ����2����������������޸�			 		�𰸣�����DB��Redis����ͬ�����޸�
 * ����3���ٽ�ֵ���ж�								�𰸣��޸�ʱ���ȡ���ݿ��е�ʣ���Ͷ����R������R=0���ж��Ƿ��ѽ��������ѽ�����ִ�н�������B
 * ����4�����������������޸�����ʱ�����δ�����������B  �𰸣����޸���������Ŀ״̬���ж����ѽر����Ŀ���ٴ�����
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
		//����У�飬�û��˻����㡢��Ŀ���ǳ��ڿ�Ͷ��״̬�µ����󶼴��
		validationCheck(uid, money, prjId);
		//ִ�����괦��
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
				result = "Ͷ�ʳɹ�";
			} catch (Exception ex) {
				return syncDBAmountToRedis(key, prjId);
			}
		} else {
			returnRemainingAmount(money.doubleValue(), key);
			result = "ʣ���Ͷ���㣬Ͷ��ʧ��!";
		}
		return result;
	}
	
	
	/**
	 * ��������ҵ���߼�������������ֹ���������������ȣ�
	 * �п��ܴ�������
	 * �Ľ���ʩ����ͬ�����ݿ⵽redis�Ĳ�����Ϊ��ʱ����������
	 * ��ʱ�����п�����ɻ������ݲ���ȷ���ڿ���������Ч��ʩ
	 * @param key
	 * @param prjId
	 * @return
	 */
	private String syncDBAmountToRedis(String key, Long prjId) {
		String result = "��Ͷ�ʶ�������꣬Ͷ�����";
		RedisLock lock = new RedisLock(redisTemplate, key);
		try {
			if (lock.lock()) {
				if (!triggered) {
					BigDecimal dbRemaining = goodService.getRemainAmountFromDB(prjId);
					if (dbRemaining.compareTo(BigDecimal.ZERO) == 0) {
						doTriggerEndBidOperation(prjId);
						triggered = true;
					} else {
						result = "ʣ���Ͷ���㣬Ͷ��ʧ��!";
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
		//�˴��������Ʊ�֤ԭ����
		double remainingAmount = redisTemplate.boundValueOps(key).increment(money*(-1));
		if (remainingAmount >= 0) {
			return BigDecimal.valueOf(remainingAmount);
		} else {
			redisTemplate.boundValueOps(key).increment(money);
			return BigDecimal.valueOf(-1);
		}
	}
	
	/**
	 * ҵ����ʧ�ܣ��˻�ռ�õ�ʣ���Ͷ����
	 * @param money
	 * @param key
	 */
	private void returnRemainingAmount(double money, String key) {
		redisTemplate.boundValueOps(key).increment(money);
	}
	
}



