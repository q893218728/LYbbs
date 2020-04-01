package com.ly.bbs.entity;

import lombok.Data;

@Data
public class Comment {
    private Integer id;
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private String createTime;
    private String updateTime;
    private String likeCount;
    private String comment;
    //评论者
    private User user;
    //下级评论的数量，对一级评论无用，对二级评论有用
    private Integer count;
    private String nickname;
    private Integer receiver;



}
