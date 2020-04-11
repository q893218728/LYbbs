package com.ly.bbs.cache;

import java.util.HashMap;
import java.util.Map;

public class HotTagCache {
    private static Map<String,Integer> tags = new HashMap<>();

    public static Map<String,Integer> getTags(){
        return tags;
    }
}
