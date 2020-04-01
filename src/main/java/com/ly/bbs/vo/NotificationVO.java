package com.ly.bbs.vo;

import com.ly.bbs.entity.User;
import lombok.Data;

@Data
public class NotificationVO {
    private User noticeUser;
    private User receiveUser;
    private String message;
    private Integer questionId;
}
