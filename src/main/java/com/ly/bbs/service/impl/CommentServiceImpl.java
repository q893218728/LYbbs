package com.ly.bbs.service.impl;

import com.ly.bbs.common.NotificationStatusEnum;
import com.ly.bbs.common.NotificationTypeEnum;
import com.ly.bbs.entity.Comment;
import com.ly.bbs.entity.Notification;
import com.ly.bbs.entity.Question;
import com.ly.bbs.entity.User;
import com.ly.bbs.mapper.CommentMapper;
import com.ly.bbs.mapper.NotificationMapper;
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
    QuestionMapper questionMapper;
    @Autowired
    NotificationMapper notificationMapper;

    @Override
    public ResultVO insertComment(Comment comment) {




        //回复了一个一级评论
        if (comment.getType() == 1) {
            //这个问题更新时间
            questionMapper.updateTime(comment.getParentId());
            //更新问题的回复数量
            questionMapper.updateCommentCount(comment.getParentId());
            //插入一级评论
            commentMapper.insertComment(comment);
            //创建通知
            Notification notification = createNotify(comment);
            //插入通知
            notificationMapper.insertNotify(notification);

        }
        if (comment.getType() == 2) {
            //这个问题也要更新时间,得到二级评论的parentId是一级评论的id。
            Integer parentId = comment.getParentId();
            //得到这个一级评论，然后根据一级评论的parentId更新问题时间
            Comment comment1 = commentMapper.selectById(parentId);
            questionMapper.updateTime(comment1.getParentId());
            //更新问题的回复数量
            questionMapper.updateCommentCount(comment1.getParentId());
            //更新一级评论的时间
            commentMapper.updateTime(comment.getParentId());

            //插入评论
            commentMapper.insertComment(comment);
            //创建通知
            Notification notification = createNotify(comment);
            //插入通知
            notificationMapper.insertNotify(notification);

        }
        //更新回复数


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


    @Override
    public Notification createNotify(Comment comment) {
        Notification notification = new Notification();
        if(comment.getType()==1){
            //通知
            notification.setNotifier(comment.getCommentator());//通知者就是评论的创建者
            notification.setType(NotificationTypeEnum.REPLY_QUESTION.getType());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());

            Integer parentId = comment.getParentId();//对于一个一级评论来说，parentId就是问题的id
            Question question = questionMapper.selectQuestionById(parentId);
            notification.setOuterId(comment.getParentId());
            notification.setReceiver(question.getCreator());

        }
        if(comment.getType()==2){

            //对于二级评论，通知者是这条评论的创建者
            notification.setNotifier(comment.getCommentator());
            //对于二级评论，接受者是二级评论里的接收者
            notification.setReceiver(comment.getReceiver());
            //外键应该是一级评论的id
            notification.setOuterId(comment.getParentId());
            notification.setType(NotificationTypeEnum.REPLY_COMMENT.getType());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
        }
        return notification;

    }
}
