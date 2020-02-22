package com.ly.bbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ly.bbs.mapper")
public class BbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbsApplication.class, args);
    }

}
