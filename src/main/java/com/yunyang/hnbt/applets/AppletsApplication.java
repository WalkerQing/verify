package com.yunyang.hnbt.applets;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.yunyang.hnbt.applets.mapper")
public class AppletsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppletsApplication.class, args);
    }

}
