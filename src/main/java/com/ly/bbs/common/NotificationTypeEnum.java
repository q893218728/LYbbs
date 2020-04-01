package com.ly.bbs.common;


import lombok.Getter;

@Getter
public enum NotificationTypeEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论");
    private Integer type;
    private String message;
    NotificationTypeEnum(Integer type, String message){
        this.type = type;
        this.message = message;
    }
}
