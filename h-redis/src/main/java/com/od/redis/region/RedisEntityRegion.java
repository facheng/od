package com.od.redis.region;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;

import com.od.redis.strategy.RedisEntityRegionAccessStrategy;
import com.od.redis.util.SerializeUtil;

import redis.clients.jedis.Jedis;

/**
 * Created by wangfacheng on 2017-09-21.
 */
public class RedisEntityRegion implements EntityRegion {

    private static final String FUZZY = "*";

    private Jedis jedis;

    public RedisEntityRegion(Jedis jedis) {
        this.jedis = jedis;
    }

    public void put(Object key, Object value) throws IOException {
        getCache().set(String.valueOf(key).getBytes(), SerializeUtil.serialize(value));
    }

    public Object get(Object key) throws IOException {
        return SerializeUtil.deSerialize(getCache().get(String.valueOf(key).getBytes()));
    }

    @Override
    public EntityRegionAccessStrategy buildAccessStrategy(AccessType accessType) throws CacheException {
        return new RedisEntityRegionAccessStrategy(this);
    }

    @Override
    public boolean isTransactionAware() {
        return false;
    }

    @Override
    public CacheDataDescription getCacheDataDescription() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void destroy() throws CacheException {
        getCache().close();
    }

    @Override
    public boolean contains(Object key) {
        return getCache().exists(String.valueOf(key));
    }

    @Override
    public long getSizeInMemory() {
        return getCache().dbSize();
    }

    @Override
    public long getElementCountInMemory() {
        return getCache().dbSize();
    }

    @Override
    public long getElementCountOnDisk() {
        return 0;
    }

    @Override
    public Map toMap() {
        try {
            return getCache().keys(FUZZY).stream()
                    .collect(Collectors.toMap(e -> e, e -> getCache().get(String.valueOf(e))));
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    @Override
    public long nextTimestamp() {
        return 0;
    }

    @Override
    public int getTimeout() {
        return 0;
    }

    public Jedis getCache() {
        return jedis;
    }
}
