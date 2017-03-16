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
 * ��ɱ���ܵ�˼·
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
		
		//У�鶩�����ݺϹ���
		
		//������Ŀʣ���Ͷ���
		BigDecimal resultMoney = getRemainingAmountWithoutLock(money.doubleValue(), key);
		if (resultMoney.compareTo(new BigDecimal(0)) > 0 ) {
			try {
				//ģ��ҵ����ʱ��
				TimeUnit.MILLISECONDS.sleep(500);
				
				//���ɶ�����Ϣ
				
				//����Ͷ�����˻���Ӧ�ʽ𣬲����ֹ��������˻��Ĳ���
				//�������ʧ�ܣ������û�Ͷ��ʧ��
				
				//�����˻������ˮ��Ϣ
				
				resultInfo = "Ͷ��ɹ�!";
			} catch (Exception ex) {
				resultInfo= "ҵ���߼�����ʧ�ܣ�����ʧ��";
				returnRemainingAmount(money.doubleValue(), key);
			}
		} else {
			resultInfo= "��Ŀ����Ѿ�������!";
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
	 * ҵ����ʧ�ܣ��˻�ռ�õ�ʣ���Ͷ����
	 * @param money
	 * @param key
	 */
	private void returnRemainingAmount(double money, String key) {
		redisTemplate.boundValueOps(key).increment(money);
	}
	
}



