package com.ssd.start.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
swagger2配置类
http://127.0.0.1:8080/swagger-ui.html
 */

/**
 * @author WHD
 * @date 2020/7/23 10:23
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ssd.start.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("ssd的SpringBoot脚手架")
                        .description("ssd的SpringBoot脚手架。。。")
                        .version("1.0")
                        .contact(new Contact("脚手架","blog.csdn.net","18404983786@163.com"))
                        .license("The Apache License")
                        .licenseUrl("http://127.0.0.1:8888/")
                        .build());
    }

}
