package com.od.redis.region;

import java.util.Properties;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.od.redis.common.CacheManager;

/**
 * Created by wangfacheng on 2017-09-21.
 */
public class RedisRegionFactory extends AbstractRedisRegionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisRegionFactory.class);

    @Override
    public void start(SessionFactoryOptions settings, Properties properties) throws CacheException {

        LOGGER.info("Welcome access redis region factory");

        if (manager != null) {
            LOGGER.info("manager is already, manager {}", manager);
            return;
        }

        manager = CacheManager.getInstance();

    }

    @Override
    public void stop() {
        LOGGER.info("start stop region factory");
        manager.shutDown();
    }
}
