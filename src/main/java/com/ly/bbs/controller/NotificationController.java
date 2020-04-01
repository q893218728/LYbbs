package com.ly.bbs.controller;

import com.ly.bbs.entity.Notification;
import com.ly.bbs.service.NotificationService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @PostMapping("/listMyNotification")
    public ResultVO getMyNotification(Integer receiver){

        return notificationService.listByReceiver(receiver);
    }
    @PostMapping("/deleteNotification")
    public ResultVO deletenotificationById(Integer id){
      return notificationService.deleteById(id);
    }
    @PostMapping("/selectCount")
    public  ResultVO selectCountByReceiver(Integer receiver){
        return notificationService.selectCountByReceiver(receiver);
    }
}
