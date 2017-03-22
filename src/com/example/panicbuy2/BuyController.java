package com.example.panicbuy2;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;

import com.spring.util.GsonUtil;


/**
 * Redis充当缓存，利用list缓存请求，
 * 然后利用redis的发布和订阅功能将缓存的数据发布出去，
 * 订阅该频道的处理器开多个线程处理业务逻辑
 * 
 * 1：waitList 存放待处理的请求列表 有最大存储数量限制maxWait
 * 2：successList 存放处理成功的请求列表
 * 3：errorList 存放处理失败的请求列表
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
			result = "数据处理中，请稍后.....";
		} else {
			redisTemplate.boundValueOps(freeKey).increment(money);
			result = "剩余可投额度不足，抢标失败";
		}
		return result;
	}
	
	
	

}
