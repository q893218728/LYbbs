package com.ly.bbs.controller;

import com.ly.bbs.common.ResponseCodeEnum;
import com.ly.bbs.entity.User;
import com.ly.bbs.from.UserFrom;
import com.ly.bbs.service.UserService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResultVO register(@Valid UserFrom userFrom, BindingResult bs) {
        if(bs.hasErrors()){
              return ResultVO.error(ResponseCodeEnum.ERROR.getStatus(),bs.getFieldError().getDefaultMessage());
        }
        User user = new User();
        BeanUtils.copyProperties(userFrom,user);
        return userService.insertUser(user);
    }

    @PostMapping("/login")
    public ResultVO login(String username, String password, HttpServletRequest request){
      ResultVO resultVO =  userService.login(username,password);
      if(resultVO.isSuccess()){

          request.getSession().setAttribute("user",resultVO.getData());
      }
      return resultVO;
    }
    @GetMapping("/logout")
    public ResultVO logout(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return ResultVO.success("成功删除");
    }
}
