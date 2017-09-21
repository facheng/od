package com.od.redis.strategy;

import java.io.IOException;

import org.hibernate.cache.CacheException;
import org.hibernate.cache.internal.DefaultCacheKeysFactory;
import org.hibernate.cache.spi.EntityRegion;
import org.hibernate.cache.spi.access.EntityRegionAccessStrategy;
import org.hibernate.cache.spi.access.SoftLock;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.persister.entity.EntityPersister;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.od.redis.region.RedisEntityRegion;

/**
 * Created by wangfacheng on 2017-09-21.
 */
public class RedisEntityRegionAccessStrategy implements EntityRegionAccessStrategy {

    private RedisEntityRegion entityRegion;

    public RedisEntityRegionAccessStrategy(RedisEntityRegion entityRegion) {
        this.entityRegion = entityRegion;
    }

    @Override
    public Object generateCacheKey(Object id, EntityPersister persister, SessionFactoryImplementor factory,
            String tenantIdentifier) {
        return DefaultCacheKeysFactory.staticCreateEntityKey(id, persister, factory, tenantIdentifier);
    }

    @Override
    public Object getCacheKeyId(Object cacheKey) {
        return DefaultCacheKeysFactory.staticGetEntityId(cacheKey);
    }

    @Override
    public EntityRegion getRegion() {
        return entityRegion;
    }

    @Override
    public boolean insert(SharedSessionContractImplementor session, Object key, Object value, Object version)
            throws CacheException {
        return false;
    }

    @Override
    public boolean afterInsert(SharedSessionContractImplementor session, Object key, Object value, Object version)
            throws CacheException {
        return false;
    }

    @Override
    public boolean update(SharedSessionContractImplementor session, Object key, Object value, Object currentVersion,
            Object previousVersion) throws CacheException {
        return false;
    }

    @Override
    public boolean afterUpdate(SharedSessionContractImplementor session, Object key, Object value,
            Object currentVersion, Object previousVersion, SoftLock lock) throws CacheException {
        return false;
    }

    @Override
    public Object get(SharedSessionContractImplementor session, Object key, long txTimestamp) throws CacheException {
        try {
            return entityRegion.get(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public boolean putFromLoad(SharedSessionContractImplementor session, Object key, Object value, long txTimestamp,
            Object version) throws CacheException {

        if (region().contains(key)) {
            return false;
        }

        try {
            region().put(key, value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean putFromLoad(SharedSessionContractImplementor session, Object key, Object value, long txTimestamp,
            Object version, boolean minimalPutOverride) throws CacheException {
        if (region().contains(key)) {
            return false;
        }

        try {
            region().put(key, value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public SoftLock lockItem(SharedSessionContractImplementor session, Object key, Object version)
            throws CacheException {
        return null;
    }

    @Override
    public SoftLock lockRegion() throws CacheException {
        return null;
    }

    @Override
    public void unlockItem(SharedSessionContractImplementor session, Object key, SoftLock lock) throws CacheException {

    }

    @Override
    public void unlockRegion(SoftLock lock) throws CacheException {

    }

    @Override
    public void remove(SharedSessionContractImplementor session, Object key) throws CacheException {
        region().getCache().del(String.valueOf(key));
    }

    @Override
    public void removeAll() throws CacheException {

    }

    @Override
    public void evict(Object key) throws CacheException {
        region().getCache().del(String.valueOf(key));
    }

    @Override
    public void evictAll() throws CacheException {

    }

    private RedisEntityRegion region() {
        return entityRegion;
    }
}
