package com.od.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by wangfacheng on 2017-09-21.
 */
public class CacheUtil {

    // Redis服务器IP
    private static final String HOST = "localhost";

    // Redis的端口号
    private static final int PORT = 6379;

    // 访问密码
    private static final String USER = null;

    // 可用连接实例的最大数目，默认值为8；
    // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static final int MAX_ACTIVE = 1024;

    // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static final int MAX_IDLE = 200;

    // 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static final long MAX_WAIT = 10000;

    private static final int TIMEOUT = 10000;

    // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static final boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    private static final String DEFAULT_HOST = HOST;
    private static final int DEFAULT_PORT = PORT;
    private static final int DEFAULT_MAX_IDLE = MAX_IDLE;
    private static final long DEFAULT_MAX_WAIT = MAX_WAIT;
    private static final int DEFAULT_TIMEOUT = TIMEOUT;
    private static final boolean DEFAULT_TEST_ON_BORROW = TEST_ON_BORROW;
    private static final String DEFAULT_USER = USER;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(DEFAULT_MAX_IDLE);
            config.setMaxWaitMillis(DEFAULT_MAX_WAIT);
            config.setTestOnBorrow(DEFAULT_TEST_ON_BORROW);
            jedisPool = new JedisPool(config, DEFAULT_HOST, DEFAULT_PORT, DEFAULT_TIMEOUT, DEFAULT_USER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     * 
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            return jedisPool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     * 
     * @param jedis
     */
    public static void closeResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static void close() {
        jedisPool.close();
    }
}
