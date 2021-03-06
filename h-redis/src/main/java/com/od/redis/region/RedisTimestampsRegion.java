package com.od.redis.region;

import java.io.IOException;
import java.util.Map;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.TimestampsRegion;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

import com.od.redis.util.SerializeUtil;

import redis.clients.jedis.Jedis;

/**
 * Created by wangfacheng on 2017-09-21.
 */
public class RedisTimestampsRegion implements TimestampsRegion {

    private Jedis cache;

    public RedisTimestampsRegion(Jedis cache) {
        this.cache = cache;
    }

    private Jedis getCache() {
        return cache;
    }

    @Override
    public Object get(SharedSessionContractImplementor session, Object key) throws CacheException {
        try {
            return SerializeUtil.deSerialize(getCache().get(String.valueOf(key).getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(SharedSessionContractImplementor session, Object key, Object value) throws CacheException {
        try {
            getCache().set(String.valueOf(key).getBytes(), SerializeUtil.serialize(value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void evict(Object key) throws CacheException {
        getCache().del(String.valueOf(key));
    }

    @Override
    public void evictAll() throws CacheException {
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void destroy() throws CacheException {

    }

    @Override
    public boolean contains(Object key) {
        return false;
    }

    @Override
    public long getSizeInMemory() {
        return 0;
    }

    @Override
    public long getElementCountInMemory() {
        return 0;
    }

    @Override
    public long getElementCountOnDisk() {
        return 0;
    }

    @Override
    public Map toMap() {
        return null;
    }

    @Override
    public long nextTimestamp() {
        return 0;
    }

    @Override
    public int getTimeout() {
        return 0;
    }
}
