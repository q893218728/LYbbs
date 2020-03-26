package com.ly.bbs.controller;

import com.ly.bbs.service.QuestionService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @PostMapping("/getMyQuestion")
    public ResultVO getMyQuestion(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  Integer id){
        return questionService.listQuestionByCreator(pageNum,pageSize,id);

    }
    @PostMapping("/question")
    public ResultVO question(Integer id){
        return questionService.getQuestionAndUser(id);
    }
    @GetMapping("/question/like")
    public ResultVO dianZan(Integer id){
        return questionService.questionlike(id);
    }
}
