package com.example.panicbuy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisLock {

	private static Logger logger = LoggerFactory.getLogger(RedisLock.class);
	
	private RedisTemplate redisTemplate;
	
	private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;
	
	private String lockKey;
	
	/* ����ʱʱ�䣬��ֹ�߳��������Ժ����޵�ִ�еȴ� */
	private int expireMsecs = 60*1000;
	
	/* ���ȴ�ʱ�䣬��ֹ�̼߳��� */
	private int timeoutMsecs = 10*1000;
	
	private volatile boolean locked = false;
	
	public RedisLock(RedisTemplate redisTemplate, String lockKey) {
        this.redisTemplate = redisTemplate;
        this.lockKey = lockKey + "_lock";
    }

	
	public RedisLock(RedisTemplate redisTemplate, String lockKey, int timeoutMsecs) {
        this(redisTemplate, lockKey);
        this.timeoutMsecs = timeoutMsecs;
    }
	
	
	public RedisLock(RedisTemplate redisTemplate, String lockKey, int timeoutMsecs, int expireMsecs) {
        this(redisTemplate, lockKey, timeoutMsecs);
        this.expireMsecs = expireMsecs;
    }
	
	public String getLockKey() {
        return lockKey;
    }
	
	private String get(final String key) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] data = connection.get(serializer.serialize(key));
                    connection.close();
                    if (data == null) {
                        return null;
                    }
                    return serializer.deserialize(data);
                }
            });
        } catch (Exception e) {
            logger.error("get redis error, key : {}", key);
        }
        return obj != null ? obj.toString() : null;
    }

    private boolean setNX(final String key, final String value) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    Boolean success = connection.setNX(serializer.serialize(key), serializer.serialize(value));
                    connection.close();
                    return success;
                }
            });
        } catch (Exception e) {
            logger.error("setNX redis error, key : {}", key);
        }
        return obj != null ? (Boolean) obj : false;
    }

    private String getSet(final String key, final String value) {
        Object obj = null;
        try {
            obj = redisTemplate.execute(new RedisCallback<Object>() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    StringRedisSerializer serializer = new StringRedisSerializer();
                    byte[] ret = connection.getSet(serializer.serialize(key), serializer.serialize(value));
                    connection.close();
                    return serializer.deserialize(ret);
                }
            });
        } catch (Exception e) {
            logger.error("setNX redis error, key : {}", key);
        }
        return obj != null ? (String) obj : null;
    }
    
    
    public synchronized boolean lock() throws InterruptedException {
        int timeout = timeoutMsecs;
        while (timeout >= 0) {
            long expires = System.currentTimeMillis() + expireMsecs + 1;
            String expiresStr = String.valueOf(expires); //������ʱ��
            if (this.setNX(lockKey, expiresStr)) {
                // lock acquired
                locked = true;
                return true;
            }

            String currentValueStr = this.get(lockKey); //redis���ʱ��
            if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                //�ж��Ƿ�Ϊ�գ���Ϊ�յ�����£�����������߳�������ֵ����ڶ��������ж��ǹ���ȥ��
                // lock is expired

                String oldValueStr = this.getSet(lockKey, expiresStr);
                //��ȡ��һ��������ʱ�䣬���������ڵ�������ʱ�䣬
                //ֻ��һ���̲߳��ܻ�ȡ��һ�����ϵ�����ʱ�䣬��Ϊjedis.getSet��ͬ����
                if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                    //��ֹ��ɾ�����ǣ���Ϊkey����ͬ�ģ������˵�����������ﲻ��Ч��������ֵ�ᱻ���ǣ�������Ϊʲô����˺��ٵ�ʱ�䣬���Կ��Խ���

                    //[�ֲ�ʽ�������]:������ʱ�򣬶���߳�ǡ�ö������������ֻ��һ���̵߳�����ֵ�͵�ǰֵ��ͬ��������Ȩ����ȡ��
                    // lock acquired
                    locked = true;
                    return true;
                }
            }
            timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLIS;

            /*
                �ӳ�100 ����,  ����ʹ�����ʱ����ܻ��һ��,���Է�ֹ�������̵ĳ���,��,��ͬʱ����������,
                ֻ����һ�����̻����,�����Ķ���ͬ����Ƶ�ʽ��г���,����������һЩ����,Ҳ��ͬ����Ƶ��������,�⽫���ܵ���ǰ���������ò�������.
                ʹ������ĵȴ�ʱ�����һ���̶��ϱ�֤��ƽ��
             */
            Thread.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);

        }
        return false;
    }


    /**
     * Acqurired lock release.
     */
    public synchronized void unlock() {
        if (locked) {
            redisTemplate.delete(lockKey);
            locked = false;
        }
    }
	
	
	
	
}
