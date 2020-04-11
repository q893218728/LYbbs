package com.ly.bbs.service;

import com.ly.bbs.entity.Question;
import com.ly.bbs.vo.ResultVO;

import java.util.List;

public interface QuestionService {
     /**
      * 发布问题的方法
      * @param question
      * @return
      */
     ResultVO insertQuestion(Question question);

     /**
      * 列出主页问题的方法，带分页
      * @param pageNum
      * @param pageSize
      * @return
      */
     ResultVO listQuestion(Integer pageNum,Integer pageSize);

     /**
      * 列出我的问题的方法
      * @param pageNum
      * @param pageSize
      * @param id
      * @return
      */
     ResultVO listQuestionByCreator(Integer pageNum,Integer pageSize,Integer id);

     /**
      * 根据问题的id得到问题和发布者信息
      * @param id
      * @return
      */
     ResultVO getQuestionAndUser(Integer id);

     /**
      * 给问题点赞
      * @param id
      * @return
      */
     ResultVO questionlike(Integer id);

     /**
      * 增加阅读数
      * @param id
      * @return
      */
     ResultVO incView(Integer id);

     /**
      * 根据标签列出相关问题，并且根据本问题id，在查询的时候抛出本问题
      * @param tag
      * @param questionId
      * @return
      */
     //
     ResultVO listQuestionByTag(String tag,Integer questionId);

     /**
      * 根据搜索列出问题
      * @param searchStr
      * @param pageNum
      * @param pageSize
      * @return
      */
     ResultVO listQuestionBySearch(String searchStr,Integer pageNum,Integer pageSize);

     /**
      * 根据主页点击标签列出问题
      * @param tag
      * @return
      */
     ResultVO getQuestionByTag(String tag,Integer pageNum,Integer pageSize);
}
