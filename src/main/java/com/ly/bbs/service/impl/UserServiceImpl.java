package com.ly.bbs.service.impl;

import com.alibaba.druid.sql.dialect.phoenix.visitor.PhoenixASTVisitor;
import com.ly.bbs.common.ResponseCodeEnum;
import com.ly.bbs.entity.User;
import com.ly.bbs.mapper.UserMapper;
import com.ly.bbs.service.UserService;
import com.ly.bbs.util.MD5Util;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ResultVO listUser() {
        return ResultVO.success(userMapper.listUser());
    }


    @Override
    public ResultVO checkGithubUser(String githubId) {
        int i = userMapper.checkGithubUser(githubId);
        if (i == 1) {

            return ResultVO.error(ResponseCodeEnum.ERROR.getStatus(), "这个github用户已存在");
        } else {
            return ResultVO.success("这个github用户可用");
        }

    }

    @Override
    public ResultVO checkUsername(String username) {
        int i = userMapper.checkUsername(username);
        if (i == 1) {
            return ResultVO.error(ResponseCodeEnum.ERROR.getStatus(), "用户名已存在");
        } else {
            return ResultVO.success("用户名可用");
        }

    }

    @Override
    public ResultVO checkEmail(String email) {
        int i = userMapper.checkEmail(email);
        if (i == 1) {
            return ResultVO.error(ResponseCodeEnum.ERROR.getStatus(), "邮箱已存在");
        } else {
            return ResultVO.success("邮箱可用");
        }

    }

    @Override
    public ResultVO checkPhone(String phone) {
        int i = userMapper.checkPhone(phone);
        if (i == 1) {
            return ResultVO.error(ResponseCodeEnum.ERROR.getStatus(), "电话已存在");
        } else {
            return ResultVO.success("电话可用");
        }
    }

    //登录的具体逻辑
    @Override
    public ResultVO login(String username, String password) {
        //1查询是否有此用户
        User user = userMapper.selectByUsername(username);
        if(user == null){
            return ResultVO.error("无此用户名");
        }
        //2查询密码是否正确
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        if (md5Password.equals(user.getPassword())) {
            user.setPassword(" ");

            return ResultVO.success(user);
        }else{
            return ResultVO.error(ResponseCodeEnum.ERROR.getStatus(),"密码错误");
        }

    }

    @Override
    public ResultVO insertUser(User user) {
        //检测用户名是否存在
        ResultVO checkUsernameVo = checkUsername(user.getUsername());
        if (!checkUsernameVo.isSuccess()) {
            return ResultVO.error(checkUsernameVo.getStatus(), checkUsernameVo.getMessage());
        }
        //检测邮箱是否存在
        ResultVO checkEmailVo = checkEmail(user.getEmail());
        if (!checkEmailVo.isSuccess()) {
            return ResultVO.error(checkEmailVo.getStatus(), checkEmailVo.getMessage());
        }
        ResultVO checkPhoneVo = checkPhone(user.getPhone());
        if (!checkPhoneVo.isSuccess()) {
            return ResultVO.error(checkPhoneVo.getStatus(), checkPhoneVo.getMessage());
        }
        //对密码进行md5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        int count = userMapper.insertUser(user);
        if (count == 0) {
            return ResultVO.error(ResponseCodeEnum.ERROR.getStatus(), "注册失败");
        }
        return ResultVO.success("注册成功");
    }
}
