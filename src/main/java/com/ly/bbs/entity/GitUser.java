package com.ly.bbs.entity;

import lombok.Data;

@Data
public class GitUser {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String githubId;
    private String email;
    private String phone;
    private String createTime;
    private String updateTime;
    private String autograph;
    private String avatar_url;
}
