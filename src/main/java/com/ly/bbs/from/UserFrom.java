package com.ly.bbs.from;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Data
public class UserFrom {

    private Integer id;

    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
    @Pattern(regexp = "^([0-9A-Za-z\\-_\\.]+)@([0-9a-z]+\\.[a-z]{2,3}(\\.[a-z]{2})?)$",message = "邮箱格式错误")
    private String email;
    @Pattern(regexp = "^1(3|4|5|6|7|8|9)\\d{9}$",message = "电话格式错误")
    private String phone;
    private String name;

}
