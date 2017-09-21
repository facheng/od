package com.od.redis.region;

import java.io.IOException;
import java.io.Serializable;

import org.junit.Before;
import org.junit.Test;

import com.od.redis.common.CacheManager;

/**
 * Created by wangfacheng on 2017-09-21.
 */
public class RedisEntityRegionTest {

    private RedisEntityRegion entityRegion;

    @Before
    public void setup() {
        entityRegion = new RedisEntityRegion(CacheManager.getInstance().getCache());
    }

    @Test
    public void toMapTest() throws IOException {

        String key = "key1";
        String value = "v1";
        entityRegion.put(key, new Hello());

        System.out.print(entityRegion.get(key).getClass());

    }

    public static class Hello implements Serializable {
        public String name = "12312";
    }
}
