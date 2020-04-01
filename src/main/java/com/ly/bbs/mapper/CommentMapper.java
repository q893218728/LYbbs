package com.ly.bbs.mapper;

import com.ly.bbs.entity.Comment;

import java.util.List;

public interface CommentMapper {
    Integer insertComment(Comment comment);
    List<Comment> listCommentByParentId(Integer parentId);
    List<Comment> listTwoCommentByParentId(Integer parentId);
    Integer updateTime(Integer id);
    /*根据一级评论的id查找属于它的二级评论的数量*/
    Integer selectCountById(Integer id);
    Comment selectById(Integer id);

}
