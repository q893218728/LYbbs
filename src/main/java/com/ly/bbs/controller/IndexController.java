package com.ly.bbs.controller;

import com.ly.bbs.entity.GithubUser;
import com.ly.bbs.entity.Question;
import com.ly.bbs.entity.User;
import com.ly.bbs.okhttpclient.Client;
import com.ly.bbs.service.QuestionService;
import com.ly.bbs.service.UserService;
import com.ly.bbs.vo.ResultVO;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@CrossOrigin
public class IndexController {

    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;

    /**
     * 用来判断用户在不在
     * @param request
     * @return
     */
    @GetMapping("/ajax")
    public ResultVO ajax(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");

        return ResultVO.success(user);
    }

    /**
     * 列出主页的所有问题，分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getQuestion")
    public ResultVO getQuestion(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize){
        return questionService.listQuestion(pageNum,pageSize);
    }

    /**
     * 根据id得到当前登陆者信息
     * @param id
     * @return
     */
    @PostMapping("/getLoginUser")
    public ResultVO getLoginUser(Integer id){
        return userService.selectById(id);
    }

    /**
     * 供过滤器使用
     * @return
     */
    @PostMapping("/loginAlert")
    public ResultVO loginAlert(){
        return ResultVO.error("请先登录");
    }
    @PostMapping("/search")
    public ResultVO search(String searchStr,@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize){

        return questionService.listQuestionBySearch(searchStr,pageNum,pageSize);
    }
}
