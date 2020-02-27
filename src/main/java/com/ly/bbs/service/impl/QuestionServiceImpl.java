package com.ly.bbs.service.impl;

import com.ly.bbs.entity.Question;
import com.ly.bbs.mapper.QuestionMapper;
import com.ly.bbs.service.QuestionService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Override
    public ResultVO insertQuestion(Question question) {
        return ResultVO.success(questionMapper.insertQuestion(question));
    }

    @Override
    public ResultVO listQuestion() {
        return ResultVO.success(questionMapper.listQuestion());
    }
}
