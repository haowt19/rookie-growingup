package com.spring.redis.test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.activemq.util.TimeUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;

import com.spring.redis.dao.IOrderDao;
import com.spring.redis.dao.IUserDao;
import com.spring.redis.entity.PrjOrder;


/**
 * 实现List<PrjOrder> orderList = new ArrayList<PrjOrder>(100)
 * 类型的增删改查
 */
public class OrderListTest {
	
	private IOrderDao orderDao;
	
	private String key = "value";
	
	RedisTemplate<String, ?> redisTemplate;
	
	@Test
	public void panicBuying() throws Exception {
		String path="WebRoot/WEB-INF/springConf/springmvc-redis.xml"; 
		ApplicationContext context = new FileSystemXmlApplicationContext(path);
		redisTemplate = (RedisTemplate<String, ?>)context.getBean("redisTemplate");
		orderDao = (IOrderDao) context.getBean("orderDao");
		
		
		
	}
	
	/**
	 * test value operations
	 */
	public void valueOperations() {
		key = "value";
		BoundValueOperations<String, Integer> valueOperations = (BoundValueOperations<String, Integer>) redisTemplate.boundValueOps(key);
		valueOperations.set(100);
		String value = valueOperations.get(0, valueOperations.size());
		System.out.println(value);
		
	}
	
	
	
	public void pushQueue() {
		Long orderNum = 100L;
		
		System.out.println("入队列的顺序为：");
		for (long i=0; i<orderNum; i++) {
			Map<String, Object> order = new HashMap<String, Object>();
			order.put("id", i);
			order.put("prjId",1123L);
			order.put("uid", 1000+i);
			order.put("money",new BigDecimal(i));
			System.out.println(order);
			orderDao.push(key, order);
		}
	}
	
	
	public void popQueue() {
		System.out.println("出队列的顺序为：");
		Long length = orderDao.length(key);
		BigDecimal totalAmount = BigDecimal.valueOf(10000);
		while (length > 0) {
			Map<String, Object> order = (Map<String, Object>)orderDao.pop(key);
			System.out.println(order);
			BigDecimal money = (BigDecimal)order.get("money");
			
			length --;
		}
	}
	
}
