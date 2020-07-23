package com.ssd.start.util;

import com.ssd.start.entity.RbacUser;

import java.util.Optional;

/**
 * @author WHD
 * @date 2020/7/23 16:24
 */
public class LoginContextHolder {

    private static final ThreadLocal<RbacUser> requestAttributesHolder = new ThreadLocal<>();

    public LoginContextHolder() {
    }

    public static void setLoginAttributes(RbacUser attributes) {
        requestAttributesHolder.set(attributes);
    }

    public static RbacUser getRequestAttributes() {
        return Optional.ofNullable(requestAttributesHolder.get()).orElse(null);
    }

    public static void clearRequestAttributes() {
        requestAttributesHolder.remove();
    }
}
