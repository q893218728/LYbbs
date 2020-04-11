package com.ly.bbs.mapper;



import com.ly.bbs.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> listUser();
    int insertUser(User user);
    int checkGithubUser(String githubId);
    int checkUsername(String username);
    int checkEmail(String email);
    int checkPhone(String phone);
    User selectByUsername(String username);
    User selectByGithubId(String githubId);
    User selectById(Integer id);
    int updateUserImg(User user);
    int updateUser(User user);
    int updateUserPassword(User user);
    int updatePasswordNoUser(User user);
}
