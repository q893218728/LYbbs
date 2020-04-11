package com.ly.bbs.controller;

import com.ly.bbs.entity.File;
import com.ly.bbs.entity.User;
import com.ly.bbs.mapper.UserMapper;
import com.ly.bbs.provider.UcloudProvider;
import com.ly.bbs.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileController {
    @Autowired
    UcloudProvider ucloudProvider;
    @Autowired
    UserMapper userMapper;
    @PostMapping("/file/upload")
    public File upload(HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartfile = multipartRequest.getFile("editormd-image-file");
        File file = new File();
        try {
            String fileName = ucloudProvider.upload(multipartfile.getInputStream(),multipartfile.getContentType(),multipartfile.getOriginalFilename());
            file.setSuccess(1);
            file.setMessage("成功");
            file.setUrl(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }


      return file;
    }
    @PostMapping("/file/uploadHead")
    public ResultVO uploadhead(HttpServletRequest request,MultipartFile multipartfile){


        try {
            String fileName = ucloudProvider.upload(multipartfile.getInputStream(),multipartfile.getContentType(),multipartfile.getOriginalFilename());
            User user = (User)request.getSession().getAttribute("user");
            user.setHeadImg(fileName);
            //更改头像
            userMapper.updateUserImg(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
