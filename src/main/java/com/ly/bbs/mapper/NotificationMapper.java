package com.ly.bbs.mapper;

import com.ly.bbs.entity.Notification;

import java.util.List;

public interface NotificationMapper {
    Integer insertNotify(Notification notification);
    List<Notification> listByReceiver(Integer receiver);
}
