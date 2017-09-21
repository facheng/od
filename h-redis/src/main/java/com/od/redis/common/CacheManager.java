package com.od.redis.common;

import com.od.redis.util.CacheUtil;

import redis.clients.jedis.Jedis;

/**
 * Created by wangfacheng on 2017-09-21.
 */
public class CacheManager {

    private static final CacheManager INSTANCE = new CacheManager();

    private CacheManager() {
    }

    public static CacheManager getInstance() {
        return INSTANCE;
    }

    public Jedis getCache() {
        return CacheUtil.getJedis();
    }

    public void shutDown() {
        CacheUtil.close();
    }
}
