package com.fh.shop.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
/* 定时器 */
@EnableScheduling
@ComponentScan("com.fh.shop.api.exception")
@ComponentScan("com.fh.shop.api.*.controller")
@MapperScan(basePackages = {"com.fh.shop.api.*.mapper"})
public class ApiSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiSpringbootApplication.class, args);
    }

}
