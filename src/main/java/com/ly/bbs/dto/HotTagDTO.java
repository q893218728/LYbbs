package com.ly.bbs.dto;

import lombok.Data;

@Data
public class HotTagDTO implements Comparable{
    private String name;
    private Integer priority;


    @Override
    public int compareTo(Object hotTagDTO) {
        HotTagDTO hotTagDTO1 = (HotTagDTO) hotTagDTO;
        return hotTagDTO1.getPriority()-this.priority;
    }
}
