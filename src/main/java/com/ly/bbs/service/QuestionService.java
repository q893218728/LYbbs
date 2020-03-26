package com.ly.bbs.service;

import com.ly.bbs.entity.Question;
import com.ly.bbs.vo.ResultVO;

import java.util.List;

public interface QuestionService {
     ResultVO insertQuestion(Question question);
     ResultVO listQuestion(Integer pageNum,Integer pageSize);
     ResultVO listQuestionByCreator(Integer pageNum,Integer pageSize,Integer id);
     ResultVO getQuestionAndUser(Integer id);
     ResultVO questionlike(Integer id);
}
