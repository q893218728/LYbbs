package com.ly.bbs.service;

import com.ly.bbs.entity.Question;
import com.ly.bbs.vo.ResultVO;

import java.util.List;

public interface QuestionService {
     ResultVO insertQuestion(Question question);
     ResultVO listQuestion();
}
