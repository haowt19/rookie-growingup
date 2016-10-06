package com.spring.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.spring.mvc.service.IQueueService;

public class NumGenerator {

	    private static RedisTemplate<Serializable, Serializable> redisTemplate;
	    
	    static {
	    	String path="WebRoot/WEB-INF/springConf/springmvc-redis.xml"; 
			ApplicationContext context = new FileSystemXmlApplicationContext(path);
			redisTemplate = (RedisTemplate<Serializable, Serializable>)context.getBean("redisTemplate");
	    }
	    
	    
	    public static RedisTemplate<Serializable, Serializable> getRedisTemplate() {
	    	String path="WebRoot/WEB-INF/springConf/springmvc-redis.xml"; 
			ApplicationContext context = new FileSystemXmlApplicationContext(path);
			redisTemplate = (RedisTemplate<Serializable, Serializable>)context.getBean("redisTemplate");
			
			return redisTemplate;
	    }
	    
	    
	    public static <T> Object getBean(Class<T> clazz) {
	    	String path="WebRoot/WEB-INF/springConf/springmvc-redis.xml"; 
			ApplicationContext context = new FileSystemXmlApplicationContext(path);
			
			return context.getBean(clazz);
	    }
	    
	    
	    /* (non-Javadoc)    
	     * @see com.xhh.payment.common.redis.service.INumberGenerator#OrderNoGen()    
	     */
	    public static String recordNoGen(String prefix, int digit) {
	        StringBuilder sb = new StringBuilder();
	        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	        String currentDate = dateFormat.format(new Date());
	        String key = "fim_CNT1_" + currentDate;
	        String hkey = prefix + currentDate;
	        sb.append(hkey);
	        long number = redisTemplate.boundHashOps(key).increment(hkey, 1);
	        if (number == 1) { 
	            redisTemplate.expire(key, 1, TimeUnit.DAYS);
	        }
	        int len = ("" + number).length();
	        sb.append(DigitalUtil.getCharAndNumr(digit-len));//生成随机数字和字母组合长度：digit
	        sb.append(number);
	        return sb.toString();
	    }
}
