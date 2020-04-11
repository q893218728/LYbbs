package com.ly.bbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.bbs.entity.Question;
import com.ly.bbs.entity.User;
import com.ly.bbs.mapper.QuestionMapper;
import com.ly.bbs.mapper.UserMapper;
import com.ly.bbs.service.QuestionService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public ResultVO insertQuestion(Question question) {
        questionMapper.insertQuestion(question);

        return ResultVO.success("发布成功");
    }

    @Override
    public ResultVO listQuestion(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Question> questionList = questionMapper.listQuestion();
        for (Question question : questionList) {
            User user = userMapper.selectById(question.getCreator());
            question.setUser(user);
        }
        PageInfo<Question> pageInfo = new PageInfo<>(questionList);

        return ResultVO.success(pageInfo);

    }

    @Override
    public ResultVO listQuestionByCreator(Integer pageNum, Integer pageSize, Integer id) {
        PageHelper.startPage(pageNum,pageSize);
        List<Question> questionList = questionMapper.listQuestionByCreator(id);
        for (Question question : questionList) {
            User user = userMapper.selectById(id);
            question.setUser(user);
        }
        PageInfo<Question> pageInfo = new PageInfo<>(questionList);
        return ResultVO.success(pageInfo);
    }

    @Override
    public ResultVO getQuestionAndUser(Integer id) {
        Question question = questionMapper.selectQuestionById(id);
        User user = userMapper.selectById(question.getCreator());
        question.setUser(user);
        return ResultVO.success(question);
    }

    @Override
    public ResultVO questionlike(Integer id) {
        questionMapper.updateLikeCount(id);
        return ResultVO.success("点赞成功");
    }

    @Override
    public ResultVO incView(Integer id) {
        questionMapper.updateViewCount(id);
        return ResultVO.success("阅读数成功+1");
    }

    @Override
    public ResultVO listQuestionByTag(String tag,Integer quesitonId) {
       List<Question> questionList =  questionMapper.selectQuestionByTag(tag,quesitonId);
       return ResultVO.success(questionList);
    }

    @Override
    public ResultVO listQuestionBySearch(String searchStr,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Question> questionList = questionMapper.listQuestionBySearch(searchStr);
        for (Question question : questionList) {
            User user = userMapper.selectById(question.getCreator());
            question.setUser(user);
        }
        PageInfo<Question> pageInfo = new PageInfo<>(questionList);
        return ResultVO.success(pageInfo);
    }


}
