package com.ly.bbs.controller;

import com.ly.bbs.entity.Person;
import com.ly.bbs.mapper.UserMapper;
import org.apache.catalina.startup.UserConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    Person person;
    @Autowired
    UserMapper userMapper;
    @RequestMapping("/test")
    public Object test()
    {
        return userMapper.listUser();
    }
}
