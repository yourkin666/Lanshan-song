package com.example.lanshansong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

/**
 * @author yourkin666
 * @date 2024/10/29/14:24
 * @description
 */
@Configuration
public class EmailConfig {

    @Bean
    public SimpleMailMessage simpleMailMessage(){
        return new SimpleMailMessage();
    }


}
