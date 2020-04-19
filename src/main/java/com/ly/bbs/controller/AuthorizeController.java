package com.ly.bbs.controller;

import com.ly.bbs.common.ResponseCodeEnum;
import com.ly.bbs.entity.AccessToken;
import com.ly.bbs.entity.GithubUser;
import com.ly.bbs.entity.User;
import com.ly.bbs.mapper.UserMapper;
import com.ly.bbs.provider.GithubProvider;
import com.ly.bbs.service.UserService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
    UserService userService;

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
        System.out.println("接收到github传递给我们的code码");
        //这里我们要接收github提供的code码，这个码是获得令牌的唯一方式。
        AccessToken accessToken = new AccessToken();
        accessToken.setCode(code);
        System.out.println(code);
        accessToken.setState(state);
        accessToken.setClient_id(clientId);
        accessToken.setClient_secret(clientSecret);
        accessToken.setRedirect_uri(redirectUri);
        //调用将code，clien_id，clientSecret，redirect_uri传给github的方法，来获得github给我们的一个令牌token
        System.out.println(1);
        String token = githubProvider.getAccessToken(accessToken);
        System.out.println(32132131);
        //通过token来获得用户的基本信息

        User user = githubProvider.getUser(token);

        System.out.println(user);
        if (user != null) {
            user.setGithubId(String.valueOf(user.getId()));
            user.setId(null);
            System.out.println(user);
            ResultVO checkGithubUserVo = userService.checkGithubUser(user.getGithubId());

            if (checkGithubUserVo.isSuccess()) {

                userService.insertUser(user);

            }
            ResultVO vo =  userService.selectByGithubId(user.getGithubId());
            User user1 = (User)vo.getData();
            System.out.println(user1);
            request.getSession().setAttribute("user", user1);
            try {
                response.sendRedirect("http://39.97.170.253");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.sendRedirect("http://39.97.170.253");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
