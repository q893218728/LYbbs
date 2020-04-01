package com.ly.bbs.service.impl;

import com.ly.bbs.common.NotificationTypeEnum;
import com.ly.bbs.entity.Comment;
import com.ly.bbs.entity.Notification;
import com.ly.bbs.entity.Question;
import com.ly.bbs.entity.User;
import com.ly.bbs.mapper.CommentMapper;
import com.ly.bbs.mapper.NotificationMapper;
import com.ly.bbs.mapper.QuestionMapper;
import com.ly.bbs.mapper.UserMapper;
import com.ly.bbs.service.NotificationService;
import com.ly.bbs.vo.NotificationVO;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    CommentMapper commentMapper;
    @Override
    public ResultVO insertNotify(Notification notification) {
        return ResultVO.success(notificationMapper.insertNotify(notification));
    }

    @Override
    public ResultVO listByReceiver(Integer receiver) {
        List<Notification> notifications = notificationMapper.listByReceiver(receiver);
        List<NotificationVO> notificationVOS = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationVO notificationVO = new NotificationVO ();
            User noticeUser = null;
            User receiveUser = null;
            String message = null;
            noticeUser = userMapper.selectById(notification.getNotifier());
            receiveUser = userMapper.selectById(notification.getReceiver());
            notificationVO.setNoticeUser(noticeUser);
            notificationVO.setReceiveUser(receiveUser);

            //如果这个是回复问题的通知，通过outerId去问题列表查
            if(notification.getType()== NotificationTypeEnum.REPLY_QUESTION.getType()){
                 notificationVO.setQuestionId(notification.getOuterId());
                 Question question = questionMapper.selectQuestionById(notification.getOuterId());
                  message = question.getTitle();
            }
            if(notification.getType()==NotificationTypeEnum.REPLY_COMMENT.getType()){
                Comment comment = commentMapper.selectById(notification.getOuterId());
                notificationVO.setQuestionId(comment.getParentId());
                 message = comment.getComment();
            }
            notificationVO.setMessage(message);
            notificationVOS.add(notificationVO);
        }
        return ResultVO.success(notificationVOS);
    }
}
