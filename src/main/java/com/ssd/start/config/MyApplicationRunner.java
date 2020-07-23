package com.ssd.start.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/*
SpringBoot 定义系统启动任务
实现接口 ServletContextListener
实现接口 CommandLineRunner
实现接口 ApplicationRunner
 */

/**
 * @author WHD
 * @date 2020/7/23 10:13
 */
@Component
@Order(1)
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 获取命令行中 无key的参数
        List<String> nonOptionArgs = args.getNonOptionArgs();
        System.out.println("MyApplicationRunner1>>>"+nonOptionArgs);
        // 获取所有的key/value形式的参数 的 key
        Set<String> optionNames = args.getOptionNames();
        for (String key : optionNames) {
            // 根据key获取value
            System.out.println("MyApplicationRunner1>>>"+key + ":" + args.getOptionValues(key));
        }
        // 获取命令行中的所有参数
        String[] sourceArgs = args.getSourceArgs();
        System.out.println("MyApplicationRunner1>>>"+Arrays.toString(sourceArgs));
    }
}
