package com.example.lanshansong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.lanshansong.Entity.Captcha;

import java.awt.image.BufferedImage;

/**
 * @author yourkin666
 * @date 2024/10/29/13:01
 * @description
 */
public interface CaptchaService extends IService<Captcha> {
    BufferedImage getCaptcha(String uuId);

    boolean validate(Captcha captcha) throws Exception;


}
