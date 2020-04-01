package com.ly.bbs.controller;

import com.ly.bbs.service.TagService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    @Autowired
    TagService tagService;

    /**
     * 得到所有标签，发布问题时用
     * @return
     */
    @GetMapping("/getTag")
    public ResultVO getTag(){
        return tagService.listTag();
    }
}
