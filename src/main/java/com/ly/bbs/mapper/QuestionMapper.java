package com.ly.bbs.mapper;

import com.ly.bbs.entity.Question;

import java.util.List;

public interface QuestionMapper {
    int insertQuestion(Question question);
    List<Question> listQuestion();
}
