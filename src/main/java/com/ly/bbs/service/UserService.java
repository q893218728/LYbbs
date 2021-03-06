package com.ly.bbs.service;

import com.ly.bbs.entity.User;
import com.ly.bbs.vo.ResultVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    ResultVO listUser();
    ResultVO insertUser(User user);
    ResultVO checkGithubUser(String githubId);
    ResultVO checkUsername(String username);
    ResultVO checkEmail(String email);
    ResultVO checkPhone(String phone);
    ResultVO login(String username,String password);
    ResultVO selectByGithubId(String githubId);
    ResultVO selectById(Integer id);
    ResultVO updateUser(HttpServletRequest request,User user);
    ResultVO updateUserPassword(String password,String newPassword,Integer id);
    ResultVO updateUserPasswordNoUser(String username,String password);
    ResultVO sendEmail(String email);
}
