package com.ly.bbs.mapper;



import com.ly.bbs.entity.User;

import java.util.List;

public interface UserMapper {

    List<User> listUser();
    int insertUser(User user);
}
