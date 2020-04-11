package com.ly.bbs.controller;

import com.ly.bbs.cache.HotTagTasks;
import com.ly.bbs.dto.HotTagDTO;
import com.ly.bbs.service.TagService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    HotTagTasks hotTagTasks;

    /**
     * 得到所有标签，发布问题时用
     * @return
     */
    @GetMapping("/getTag")
    public ResultVO getTag(){
        return tagService.listTag();
    }
    @GetMapping("/hotTag")
    public ResultVO hotTag(){
        List<HotTagDTO> hotTagDTOList =  hotTagTasks.getHotTagList();
        return ResultVO.success(hotTagDTOList);
    }
}
