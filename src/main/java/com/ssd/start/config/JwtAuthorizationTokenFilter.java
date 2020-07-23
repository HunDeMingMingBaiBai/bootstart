package com.ssd.start.config;

import com.alibaba.fastjson.JSON;
import com.ssd.start.entity.RbacUser;
import com.ssd.start.pojo.ResultObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
token 拦截器
 */

/**
 * @author WHD
 * @date 2020/7/23 16:27
 */
@Slf4j
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = getTokenByRequest(request);
        if (StringUtils.isNotBlank(token)) {
            try {
                RbacUser user = (RbacUser) redisTemplate.opsForValue().get(token);
                if (user != null) {
                    chain.doFilter(request, response);
                } else {
                    writeExceptionInfo(request, response, chain);
                }
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        } else {
            writeExceptionInfo(request, response, chain);
        }
    }

    public void writeExceptionInfo(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        try {
            if (StringUtils.equals(request.getRequestURI(), "/login/")) {
                chain.doFilter(request, response);
            } else {
                response.setStatus(HttpStatus.OK.value());
                response.setHeader("Content-Type", "application/json;charset=UTF-8");
                PrintWriter writer = response.getWriter();
                ResultObject resultObject = ResultObject.success("token已失效，请重新登录");
                writer.write(JSON.toJSONString(resultObject));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static String getTokenByRequest(HttpServletRequest request) {
        if (request == null) {
            return null;
        } else {
            String authToken = request.getHeader("X-AUTH-TOKEN");
            if (StringUtils.isEmpty(authToken)) {
                authToken = request.getParameter("token");
            }
            return authToken;
        }
    }
}
