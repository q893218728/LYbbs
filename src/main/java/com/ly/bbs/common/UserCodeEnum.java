package com.ly.bbs.common;

import lombok.Getter;

@Getter
public enum UserCodeEnum {

    USERNAME_ISNULL(2,"用户名不存在"),
    PASSWORD_ERROR(3,"密码错误");

    private Integer status;
    private String message;

    UserCodeEnum(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
