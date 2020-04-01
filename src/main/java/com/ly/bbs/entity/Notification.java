package com.ly.bbs.entity;

import lombok.Data;

@Data
public class Notification {
    private Integer id;
    private Integer notifier;//发送通知的人
    private Integer receiver;//接收通知的人
    private Integer outerId;//因为通知可能是评论，可能是回复问题，可能是点赞。不确定类型。
    private Integer type;//区分到底是什么通知类型
    private String createTime;
    private Integer status;//通知状态
}
