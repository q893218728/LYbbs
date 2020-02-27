package com.ly.bbs.controller;

import com.ly.bbs.entity.Question;
import com.ly.bbs.service.QuestionService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发帖子的Controller
 */
@RestController
public class PublishController {
    @Autowired
    QuestionService questionService;
    @PostMapping("/publish")
    public ResultVO publish(Question question){
          return  questionService.insertQuestion(question);
    }
}
