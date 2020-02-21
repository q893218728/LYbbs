package com.ly.bbs.controller;

import com.ly.bbs.entity.GithubUser;
import com.ly.bbs.okhttpclient.Client;
import com.ly.bbs.vo.ResultVO;
import okhttp3.Request;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@CrossOrigin
public class AjaxController {
    @GetMapping("/ajax")

    public ResultVO ajax(HttpServletRequest request){
        GithubUser githubUser = (GithubUser) request.getSession().getAttribute("githubUser");
        System.out.println(githubUser + "ajax");
        return ResultVO.success(githubUser);
    }

    /*@GetMapping("/fa")
    public void fa(){
        System.out.println(111);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/authorize?client_id=2df2e14cfbc439aaa52a&redirect_uri=http://localhost:8888/callback&scope=user&state=1")
                .build();

        try {
            Client.INSTANCE.getInstance().newCall(request).execute();
            System.out.println(22222);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }*/
}
