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

    /**
     * 列出我的问题
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    @PostMapping("/getMyQuestion")
    public ResultVO getMyQuestion(@RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "5") Integer pageSize,
                                  Integer id){
        return questionService.listQuestionByCreator(pageNum,pageSize,id);

    }

    /**
     * 问题详情，列出问题和所有者
     * @param id
     * @return
     */
    @PostMapping("/question")
    public ResultVO question(Integer id){
        //增加阅读数
         questionService.incView(id);
         //返回问题和发起者的信息
        return questionService.getQuestionAndUser(id);
    }

    /**
     * 根据标签和问题的id，列出本问题外的相关问题。这里的标签仅是第一个标签
     * @param tag
     * @param questionId
     * @return
     */
    @PostMapping("/question/listByTag")
    public ResultVO listByTag(String tag,Integer questionId){
        return questionService.listQuestionByTag(tag,questionId);
    }

    /**
     * 点赞，需要问题的id
     * @param id
     * @return
     */
    @GetMapping("/question/like")
    public ResultVO dianZan(Integer id){
        return questionService.questionlike(id);
    }
}
