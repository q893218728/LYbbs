package com.ly.bbs.cache;

import com.ly.bbs.entity.Question;
import com.ly.bbs.mapper.QuestionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class HotTagTasks {
   /* @Autowired
    QuestionMapper questionMapper;
    @Scheduled(fixedRate = 10000)
    public void hotTagSchedule(){
        int offset = 0;
        int limit = 20;

        Map<String,Integer> priorities = HotTagCache.getTags();
        List<Question> questionList = new ArrayList<>();
        questionList = questionMapper.listQuestion();
        for (Question question : questionList) {
            String[] tags = StringUtils.split(question.getTag(),",");
            for (String tag : tags) {
                //权重
              Integer priority = priorities.get(tag);

            }
        }
    }*/
}
