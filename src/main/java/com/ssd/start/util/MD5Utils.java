package com.ssd.start.util;

import org.springframework.util.DigestUtils;

/**
 * MD5加密工具类
 * @author WHD
 * @date 2020/7/23 15:51
 */
public class MD5Utils {

    public static String md5Encode(String text){
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }

}
