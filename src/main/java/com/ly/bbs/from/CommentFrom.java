package com.ly.bbs.from;

import com.ly.bbs.entity.User;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class CommentFrom {
    private Integer parentId;
    private Integer type;
    private Integer commentator;
    private String createTime;
    private String updateTime;
    @Pattern(regexp = "^[\\s\\S]{10,}$",message = "回复内容请大于10个字")
    private String comment;
    private String belong;
    //下级评论的数量，对一级评论无用，对二级评论有用
    private Integer count;

    private List threeCommentList;
    private Integer answerId;
    //回复者，三级评论用
    private User answer;
}
