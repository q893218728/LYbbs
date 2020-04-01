package com.ly.bbs.service;

import com.ly.bbs.entity.Comment;
import com.ly.bbs.entity.Notification;
import com.ly.bbs.vo.ResultVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface CommentService {
    /**
     * 插入一个评论
     * @param comment
     * @return
     */
    ResultVO insertComment(Comment comment);

    /**
     * 根据问题id得到所有一级评论
     * @param parentId
     * @return
     */
    ResultVO selectCommentByParentId(Integer parentId);

    /**
     * 根据一级评论id得到所有二级评论
     * @param parentId
     * @return
     */
    ResultVO selectTwoCommentByParentId(Integer parentId);
    Notification createNotify(Comment comment);
}
