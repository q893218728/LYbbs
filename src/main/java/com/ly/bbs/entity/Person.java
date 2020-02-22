package com.ly.bbs.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "person")
@Component
public class Person {
    private String name;
    private int age;
}
