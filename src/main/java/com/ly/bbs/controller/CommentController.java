package com.ly.bbs.controller;

import com.ly.bbs.entity.Comment;
import com.ly.bbs.from.CommentFrom;
import com.ly.bbs.service.CommentService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;
    @PostMapping("/comment")
    public ResultVO recive(@Valid CommentFrom commentFrom, BindingResult bs){
        if(bs.hasErrors()){
            return ResultVO.error(bs.getFieldError().getDefaultMessage());
        }else{
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentFrom,comment);
            return commentService.insertComment(comment);
        }

    }
    @PostMapping("/commentList")
    public ResultVO commentList(Integer parentId,Integer type){

        /*获得一级评论*/
       if(type==1){
           return  commentService.selectCommentByParentId(parentId);
       }
       /*获取二级评论*/
       if(type==2){
          return  commentService.selectTwoCommentByParentId(parentId);
       }
       return ResultVO.error("传递参数type错误");

    }


}
