package com.example.lanshansong.service.impl;

import com.example.lanshansong.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author yourkin666
 * @date 2024/10/29/13:46
 * @description
 */
@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String fromName;



    @Override
    @Async
    public void send(String email, String context) {
        simpleMailMessage.setSubject("幸运日");
        simpleMailMessage.setFrom(fromName);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText(context);
        javaMailSender.send(simpleMailMessage);
    }
}
