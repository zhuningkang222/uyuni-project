package com.uyuni.main.lock;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import redis.clients.jedis.Jedis;

public class RedisTool {
	
	private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
    	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println("["+f.format(new Date())+"]"+Thread.currentThread().getName() + "尝试获取锁,"+"lockKey:"+lockKey+"requestId:"+requestId);
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
        	System.out.println("["+f.format(new Date())+"]"+Thread.currentThread().getName() + "获取锁成功!"+"lockKey:"+lockKey+",requestId:"+requestId);
            return true;
        }
        return false;

    }
    
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        
        if (RELEASE_SUCCESS.equals(result)) {
        	//SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	//System.out.println("["+f.format(new Date())+"]"+Thread.currentThread().getName() + "释放锁成功!"+"lockKey:"+lockKey+"requestId:"+requestId);
            return true;
        }
        return false;

    }

}
