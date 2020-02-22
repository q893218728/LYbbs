package com.ly.bbs.controller;

import com.ly.bbs.entity.AccessToken;
import com.ly.bbs.entity.GithubUser;
import com.ly.bbs.entity.User;
import com.ly.bbs.mapper.UserMapper;
import com.ly.bbs.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 用来控制github授权的controller类
 */
@Controller
public class AuthorizeController {
   /* @Value("${myname}")
    private String name;*/

    @Autowired
    GithubProvider githubProvider;
    @Autowired
    UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public void callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request, HttpServletResponse response) {
        //这里我们要接收github提供的code码，这个码是获得令牌的唯一方式。
        AccessToken accessToken = new AccessToken();
        accessToken.setCode(code);
        accessToken.setState(state);
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(clientSecret);
        accessToken.setRedirect_uri(redirectUri);
        //调用将code，clien_id，clientSecret，redirect_uri传给github的方法，来获得github给我们的一个令牌token
        String token = githubProvider.getAccesstoken(accessToken);
        //通过token来获得用户的基本信息
        GithubUser githubUser = githubProvider.getUser(token);

        if (githubUser != null) {
            User user = new User();
            user.setAccontId(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setToken(String.valueOf(githubUser.getId()));
            userMapper.insertUser(user);
            request.getSession().setAttribute("githubUser",githubUser);
            try {
                response.sendRedirect("http://localhost:63343/bbsFrontend/index.html?_ijt=i70dr4ielgitut32b5id743j2l");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.sendRedirect("http://localhost:63343/bbsFrontend/index.html?_ijt=i70dr4ielgitut32b5id743j2l");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
