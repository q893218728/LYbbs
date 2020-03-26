package com.ly.bbs.mapper;

import com.ly.bbs.entity.Question;

import java.util.List;

public interface QuestionMapper {
    int insertQuestion(Question question);
    List<Question> listQuestion();
    List<Question> listQuestionByCreator(Integer id);
    Question selectQuestionById(Integer id);
    /**
     * 点赞的方法*/
    Integer updateLikeCount(Integer id);
}
