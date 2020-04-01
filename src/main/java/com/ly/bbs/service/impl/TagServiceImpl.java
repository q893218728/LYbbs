package com.ly.bbs.service.impl;

import com.ly.bbs.entity.Tag;
import com.ly.bbs.mapper.TagMapper;
import com.ly.bbs.service.TagService;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Override
    public ResultVO listTag() {
        List<Tag> tagList = tagMapper.listTag();
        return ResultVO.success(tagList);
    }
}
