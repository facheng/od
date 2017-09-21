package com.od.redis.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by wangfacheng on 2017-09-21.
 */
public final class ObjectMapperUtil {

    private static final ObjectMapper INSTANCE = new ObjectMapper();

    public static final ObjectMapper getInstance() {
        return INSTANCE;
    }
}
