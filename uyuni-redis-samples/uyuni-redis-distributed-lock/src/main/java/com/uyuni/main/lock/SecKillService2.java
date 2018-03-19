package com.uyuni.main.lock;

import java.util.UUID;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class SecKillService2 {
	
	
	private static JedisPool pool = null;
	
	int n = 500;
	
	static {
		JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "127.0.0.1", 6379, 3000);
        
	}
	
	public void secKill() {
		String lockKey = "lock:uyuni";
		String requestId = UUID.randomUUID().toString();
		Jedis jedis = pool.getResource();
		boolean flag = false;
		while (!flag) {
			flag = RedisTool.tryGetDistributedLock(jedis, lockKey, requestId, 3000);
			if(flag) System.out.println(--n);
		}
		//RedisTool.releaseDistributedLock(jedis, lockKey, requestId);
	}

}
