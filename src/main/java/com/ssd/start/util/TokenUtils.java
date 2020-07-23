package com.ssd.start.util;

import java.util.UUID;

/**
 * token生成工具类
 * @author WHD
 * @date 2020/7/23 15:29
 */
public class TokenUtils {

    public static String generateToken(String redisKey) {
        return redisKey.toLowerCase() + UUID.randomUUID().toString().replace("-", "");
    }

    private TokenUtils(){}
}
