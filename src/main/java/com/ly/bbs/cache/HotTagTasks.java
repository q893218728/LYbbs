package com.ly.bbs.cache;

import com.ly.bbs.dto.HotTagDTO;
import com.ly.bbs.entity.Question;
import com.ly.bbs.mapper.QuestionMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

@Component
@Slf4j

public class HotTagTasks {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private HotTagCache hotTagCache;

    private List hotTagList;

    public List getHotTagList() {
        return hotTagList;
    }

    @Scheduled(fixedRate = 10000000)
    public void hotTagSchedule(){

        Map<String,Integer> prioritiesMap = hotTagCache.getTags();
        List<Question> questionList =  questionMapper.listQuestion();

        //设计权重为 这个标签下一个问题+5点 一个评论+1点 一个赞+2点
        for (Question question : questionList) {
            String tags = question.getTag();
            if(tags.contains(",")){
                String[] tagsplit = tags.split(",");
                for (String tag : tagsplit) {
                    //权重
                    Integer priority = prioritiesMap.get(tag);
                    if(priority!=null){
                        prioritiesMap.put(tag,priority+ 5 + question.getCommentCount()+2*question.getLikeCount());
                    }else{
                        prioritiesMap.put(tag,5 + question.getCommentCount()+2*question.getLikeCount());
                    }

                }
            }else{

                Integer priority = prioritiesMap.get(tags);
                if(priority!=null){
                    prioritiesMap.put(tags,priority+ 5 + question.getCommentCount()+2*question.getLikeCount());
                }else{
                    prioritiesMap.put(tags,5 + question.getCommentCount()+2*question.getLikeCount());
                }
            }

        }



        hotTagList = hotTagCache.updateHotTags(prioritiesMap);
        prioritiesMap.clear();
    }
}
