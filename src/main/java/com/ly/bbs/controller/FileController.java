package com.ly.bbs.controller;

import com.ly.bbs.entity.File;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
    @PostMapping("/file/upload")
    public File upload(){
        File file = new File();
        file.setSuccess(1);
        file.setMessage("成功");
        file.setUrl("img/MyChat.png");
      return file;
    }
}
