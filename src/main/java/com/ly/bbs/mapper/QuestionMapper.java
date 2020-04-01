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
    Integer updateViewCount(Integer id);
    /*更新问题的时间*/
    Integer updateTime(Integer id);
    List<Question> selectQuestionByTag(String tag,Integer questionId);
    List<Question> listQuestionBySearch(String searchStr);
}
