package com.ssd.start.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
统一异常处理
 */

/**
 * @author WHD
 * @date 2020/7/23 9:46
 */
@ControllerAdvice
public class ControllerAdviceConfig {

    @ExceptionHandler(Exception.class)
    public String customException(Exception e){
        return e.getMessage();
    }

}
