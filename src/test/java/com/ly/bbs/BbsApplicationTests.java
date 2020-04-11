package com.ly.bbs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
class BbsApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;
    @Test
    void contextLoads() {
    }
    @Test
    public void s(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("614714303@qq.com");
        message.setSubject("这是一封测试邮件");
        message.setText("");
        message.setFrom("614714303@qq.com");
        javaMailSender.send(message);
    }

}
