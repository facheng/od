package com.od.redis.region;

import java.util.Properties;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.spi.CacheDataDescription;
import org.hibernate.cache.spi.CollectionRegion;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.NaturalIdRegion;
import org.hibernate.cache.spi.QueryResultsRegion;
import org.hibernate.cache.spi.RegionFactory;
import org.hibernate.cache.spi.TimestampsRegion;
import org.hibernate.cache.spi.access.AccessType;

import com.od.redis.common.CacheManager;

import redis.clients.jedis.Jedis;

/**
 * Created by wangfacheng on 2017-09-21.
 */
abstract class AbstractRedisRegionFactory implements RegionFactory {

    protected CacheManager manager;

    @Override
    public boolean isMinimalPutsEnabledByDefault() {
        return false;
    }

    @Override
    public AccessType getDefaultAccessType() {
        return AccessType.READ_WRITE;
    }

    @Override
    public long nextTimestamp() {
        return 0;
    }

    @Override
    public EntityRegion buildEntityRegion(String regionName, Properties properties, CacheDataDescription metadata)
            throws CacheException {
        return new RedisEntityRegion(getCache());
    }

    @Override
    public NaturalIdRegion buildNaturalIdRegion(String regionName, Properties properties, CacheDataDescription metadata)
            throws CacheException {
        return null;
    }

    @Override
    public CollectionRegion buildCollectionRegion(String regionName, Properties properties,
            CacheDataDescription metadata) throws CacheException {
        return null;
    }

    @Override
    public QueryResultsRegion buildQueryResultsRegion(String regionName, Properties properties) throws CacheException {
        return new RedisQueryResultsRegion(getCache());
    }

    @Override
    public TimestampsRegion buildTimestampsRegion(String regionName, Properties properties) throws CacheException {
        return new RedisTimestampsRegion(getCache());
    }

    private Jedis getCache() {
        return manager.getCache();
    }

}
