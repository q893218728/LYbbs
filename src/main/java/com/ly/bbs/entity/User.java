package com.ly.bbs.entity;




import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String accontId;
    private String name;
    private String token;
    private Date createTime;
    private Date updateTime;
}
