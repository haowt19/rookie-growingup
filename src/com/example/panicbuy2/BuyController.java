package com.example.panicbuy2;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;

import com.spring.util.GsonUtil;


/**
 * Redis�䵱���棬����list��������
 * Ȼ������redis�ķ����Ͷ��Ĺ��ܽ���������ݷ�����ȥ��
 * ���ĸ�Ƶ���Ĵ�����������̴߳���ҵ���߼�
 * 
 * 1��waitList ��Ŵ�����������б� �����洢��������maxWait
 * 2��successList ��Ŵ���ɹ��������б�
 * 3��errorList ��Ŵ���ʧ�ܵ������б�
 * 
 * @author 001244
 *
 */
public class BuyController {
	
	private RedisTemplate redisTemplate;
	
	public String doBuy(HttpServletRequest request) {
		String freeKey = "freeAmount";
		String waitkey = "waitList";
		String successKey = "successList";
		String errorKey = "errorList";
		String result = "";
		
		double money = getParameterFromRequest("money");
		Double cacheRemaining = redisTemplate.boundValueOps(freeKey).increment(money*-1);
		if (cacheRemaining >= 0) {
			String jsonReq = GsonUtil.fromObjToJson(request);
			redisTemplate.boundListOps(waitkey).leftPush(jsonReq);
			result = "���ݴ����У����Ժ�.....";
		} else {
			redisTemplate.boundValueOps(freeKey).increment(money);
			result = "ʣ���Ͷ��Ȳ��㣬����ʧ��";
		}
		return result;
	}
	
	
	

}
