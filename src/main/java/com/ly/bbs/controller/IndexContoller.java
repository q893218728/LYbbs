package com.ly.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexContoller {
    @GetMapping("/")
    public String  index(){
        return "index";
    }
}
