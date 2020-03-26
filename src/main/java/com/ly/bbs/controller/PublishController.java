package com.ly.bbs.controller;

import com.ly.bbs.entity.Question;
import com.ly.bbs.from.QuestionFrom;
import com.ly.bbs.service.QuestionService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 发帖子的Controller
 */
@Controller
@ResponseBody
public class PublishController {
    @Autowired
    QuestionService questionService;
    @PostMapping("/publish")
    public ResultVO publish(@Valid QuestionFrom questionFrom, BindingResult bs){
        System.out.println(1111);
        if(bs.hasErrors()){
            return ResultVO.error(bs.getFieldError().getDefaultMessage());
        }
        Question question = new Question();
        BeanUtils.copyProperties(questionFrom,question);
        System.out.println(222222);
        return  questionService.insertQuestion(question);
    }
}
