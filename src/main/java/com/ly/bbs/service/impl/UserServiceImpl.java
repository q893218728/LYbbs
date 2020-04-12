package com.ly.bbs.service.impl;

import com.alibaba.druid.sql.dialect.phoenix.visitor.PhoenixASTVisitor;
import com.ly.bbs.common.ResponseCodeEnum;
import com.ly.bbs.entity.User;
import com.ly.bbs.mapper.UserMapper;
import com.ly.bbs.service.UserService;
import com.ly.bbs.util.MD5Util;
import com.ly.bbs.vo.ResultVO;
import org.hibernate.validator.internal.util.privilegedactions.NewProxyInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    JavaMailSenderImpl javaMailSender;
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
    public ResultVO selectByGithubId(String githubId) {
        User user = userMapper.selectByGithubId(githubId);
        return ResultVO.success(user);
    }

    @Override
    public ResultVO selectById(Integer id) {
        User user = userMapper.selectById(id);
        return ResultVO.success(user);
    }

    @Override
    public ResultVO updateUser(HttpServletRequest request, User user) {
        userMapper.updateUser(user);
        User user1 = userMapper.selectById(user.getId());
        HttpSession session = request.getSession();
        session.setAttribute("user",user1);
        //完成之后我们应该更改一下session中存在的用户，因为此时只是数据库变了。session中的用户没变，而我经常用到。所以给他改过来

        return ResultVO.success();
    }

    @Override
    public ResultVO updateUserPassword(String password,String newPassword,Integer id) {
        User user = userMapper.selectById(id);
        String MD5Password = MD5Util.MD5EncodeUtf8(password);
        if(MD5Password.equals(user.getPassword())){
            //证明密码没问题，进行更改
            String MD5NewPassword = MD5Util.MD5EncodeUtf8(newPassword);
            user.setPassword(MD5NewPassword);
            userMapper.updateUserPassword(user);
            return ResultVO.success();
        }else{
            return ResultVO.error("原密码不正确");
        }

    }

    @Override
    public ResultVO updateUserPasswordNoUser(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if(user==null){
            return ResultVO.error("请输入正确的用户名");
        }else {
            String MD5password = MD5Util.MD5EncodeUtf8(password);
            user.setPassword(MD5password);
            userMapper.updatePasswordNoUser(user);
            return ResultVO.success();
        }

    }

    @Override
    public ResultVO sendEmail(String email) {
        System.out.println(email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("筑青春IT交流社区");
        message.setText("请在以下网址更改您的密码 http://localhost:63343/bbsFrontend/update.html");
        message.setFrom("614714303@qq.com");
        javaMailSender.send(message);
        return ResultVO.success();
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
