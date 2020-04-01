package com.ly.bbs.service;

import com.ly.bbs.entity.Notification;
import com.ly.bbs.vo.ResultVO;

import java.util.List;

public interface NotificationService {
    ResultVO insertNotify(Notification notification);
    ResultVO listByReceiver(Integer receiver);
    ResultVO deleteById(Integer id);
    ResultVO selectCountByReceiver(Integer receiver);
}
