package com.ly.bbs.entity;




import lombok.Data;


@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String githubId;
    private String email;
    private String phone;
    private String createTime;
    private String updateTime;

}
