package com.spring.redis.cookie;

import java.util.UUID;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.Jedis;

public class TokenCookie {

	@Test
	public void test() {
		Jedis conn = new Jedis("localhost");
		conn.select(15);
		
		loginCookie(conn);
	}
	
	public void loginCookie(Jedis conn) {
		String token = UUID.randomUUID().toString();
		
		updateToken(conn, token, "username", "redis in action");
		System.out.println("We just logged-in/updated token: " + token);
        System.out.println("For user: 'username'");
        System.out.println();
		
        System.out.println("What 'username' do we get when we look-up that token?");
        String userName = checkToken(conn, token);
		
        
        long s = conn.hlen("login:");
        System.out.println("The current number of sessions still available is: " + s);
	}
	
	/**
	 * 更新服务器端token
	 * @param conn
	 * @param token
	 * @param user 用户名
	 * @param item 商品
	 */
	public void updateToken(Jedis conn, String token, String user, String item) {
		/*update token*/
		long timeStamp = System.currentTimeMillis() / 1000;
		conn.hset("login", token, user);
		conn.zadd("lastLoginTime", timeStamp, token);
		if (item != null) {
			conn.zadd("viewed:"+token, timeStamp, item);
			conn.zremrangeByRank("viewed:"+token, 0, -26);
		}
	}
	
	/**
	 * 根据token获取相应的username
	 * @param conn
	 * @param token
	 * @return
	 */
	public String checkToken(Jedis conn, String token) {
		return conn.hget("login", token);
	}
	
	
	
}
