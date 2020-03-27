package com.ly.bbs.service.impl;

import com.ly.bbs.entity.Comment;
import com.ly.bbs.entity.Question;
import com.ly.bbs.entity.User;
import com.ly.bbs.mapper.CommentMapper;
import com.ly.bbs.mapper.QuestionMapper;
import com.ly.bbs.mapper.UserMapper;
import com.ly.bbs.service.CommentService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper ;
    @Override
    public ResultVO insertComment(Comment comment) {


        /*不管回复几级评论，这个问题都更新时间*/
        questionMapper.updateTime(comment.getParentId());
        /*回复的是一个二级评论，这个一级评论要跟新时间*/
        if(comment.getType()==1){
            commentMapper.insertComment(comment);
        }
        if(comment.getType()==2){
            commentMapper.insertComment(comment);
            commentMapper.updateTime(comment.getParentId());
        }

        return ResultVO.success("添加成功");
    }

    /*获得一级评论的方法*/
    @Override
    public ResultVO selectCommentByParentId(Integer parentId) {
       List<Comment> commentList = commentMapper.listCommentByParentId(parentId);
        for (Comment comment : commentList) {
            /*应该通过这些一级评论的id去查找到二级评论的数量作为一个显示*/
             Integer count = commentMapper.selectCountById(comment.getId());
             comment.setCount(count);
            User user = null;
            /*通过评论者id查到评论者*/
            user = userMapper.selectById(comment.getCommentator());
            comment.setUser(user);

        }
        return ResultVO.success(commentList);
    }

    @Override
    public ResultVO selectTwoCommentByParentId(Integer parentId) {
        /*根据一级评论id查到二级评论*/
        List<Comment> commentList = commentMapper.listTwoCommentByParentId(parentId);
        for (Comment comment : commentList) {
            User user = null;
            user = userMapper.selectById(comment.getCommentator());
            comment.setUser(user);

        }
        return ResultVO.success(commentList);
    }
}
