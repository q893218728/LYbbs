package com.ly.bbs.entity;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String createTime;
    private String updateTime;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
