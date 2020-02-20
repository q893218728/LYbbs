package com.ly.bbs.controller;

import com.ly.bbs.entity.GithubUser;
import com.ly.bbs.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AjaxController {
    @GetMapping("/ajax")

    public ResultVO ajax(HttpServletRequest request){
        GithubUser githubUser = (GithubUser) request.getSession().getAttribute("githubUser");
        return ResultVO.success(githubUser);
    }
}
