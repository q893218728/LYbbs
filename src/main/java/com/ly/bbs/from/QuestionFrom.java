package com.ly.bbs.from;

import com.ly.bbs.entity.User;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class QuestionFrom {
    @Pattern(regexp = "^[\\s\\S]{5,25}$",message = "请输入5-25个字之内的标题")
    private String title;
    @Pattern(regexp = "^[\\s\\S]{10,}$",message = "问题描述要大于10个字")
    private String description;

    private String createTime;
    private String updateTime;
    private Integer creator;
    private String tag;

}
