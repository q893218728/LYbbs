package com.ly.bbs.service;

import com.ly.bbs.entity.Comment;
import com.ly.bbs.vo.ResultVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public interface CommentService {
    ResultVO insertComment(Comment comment);
    ResultVO selectCommentByParentId(Integer parentId);
    ResultVO selectTwoCommentByParentId(Integer parentId);
}
