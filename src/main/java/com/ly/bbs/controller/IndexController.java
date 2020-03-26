package com.ly.bbs.controller;

import com.ly.bbs.entity.GithubUser;
import com.ly.bbs.entity.Question;
import com.ly.bbs.entity.User;
import com.ly.bbs.okhttpclient.Client;
import com.ly.bbs.service.QuestionService;
import com.ly.bbs.vo.ResultVO;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@CrossOrigin
public class IndexController {

    @Autowired
    QuestionService questionService;


    @GetMapping("/ajax")
    public ResultVO ajax(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return ResultVO.success(user);
    }
    @GetMapping("/getQuestion")
    public ResultVO getQuestion(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize){
        return questionService.listQuestion(pageNum,pageSize);
    }
}
