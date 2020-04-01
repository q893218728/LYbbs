package com.ly.bbs.common;

import lombok.Getter;

@Getter
public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1);
    private Integer status;

     NotificationStatusEnum(Integer status) {
        this.status = status;
    }
}
