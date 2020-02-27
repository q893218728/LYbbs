package com.ly.bbs.service;

import com.ly.bbs.entity.User;
import com.ly.bbs.vo.ResultVO;

import java.util.List;

public interface UserService {
    ResultVO listUser();
    ResultVO insertUser(User user);
    ResultVO checkGithubUser(String githubId);
    ResultVO checkUsername(String username);
    ResultVO checkEmail(String email);
    ResultVO checkPhone(String phone);
    ResultVO login(String username,String password);
}
