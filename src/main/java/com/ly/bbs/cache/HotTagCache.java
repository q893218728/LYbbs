package com.ly.bbs.cache;

import com.ly.bbs.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.BiConsumer;

@Component
@Data
public class HotTagCache {
    private Integer size=13;
    private Integer topN=5;
    /**
     * 这个Map 前面存标签名，后面存权重
     */
    private  Map<String,Integer> tags = new HashMap<>();

    public List updateHotTags(Map<String,Integer> map){

        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(size);
        map.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String tag, Integer priority) {
                HotTagDTO hotTagDTO = new HotTagDTO();
                hotTagDTO.setName(tag);
                hotTagDTO.setPriority(priority);
                priorityQueue.add(hotTagDTO);
            }
        });
        List<HotTagDTO> list = new ArrayList<>(topN);
        for (int i = 0; i < topN; i++) {
            list.add(priorityQueue.poll());
        }

        return list;
    }

}
