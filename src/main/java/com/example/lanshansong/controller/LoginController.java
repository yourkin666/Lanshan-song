package com.example.lanshansong.controller;

import com.example.lanshansong.Entity.Captcha;
import com.example.lanshansong.Entity.user.User;
import com.example.lanshansong.Entity.vo.FindPWVO;
import com.example.lanshansong.Entity.vo.RegisterVO;
import com.example.lanshansong.service.LoginService;
import com.example.lanshansong.util.JwtUtils;
import com.example.lanshansong.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author yourkin666
 * @date 2024/10/28/18:11
 * @description
 */
@RestController
@RequestMapping("/luckyjourney/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping
    public R login(@RequestBody @Validated User user) {
        user = loginService.login(user);
        // 登录成功，生成token
        String token = JwtUtils.getJwtToken(user.getId(), user.getNickName());
        final HashMap<Object, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("name",user.getNickName());
        map.put("user",user);
        return R.ok().data(map);
    }

    /**
     * 获取图形验证码
     * @param response
     * @param uuId
     * @throws IOException
     */
    @GetMapping("/captcha.jpg/{uuId}")
    public void captcha(HttpServletResponse response, @PathVariable String uuId) throws IOException {
        loginService.captcha(uuId,response);
    }


    /**
     * 获取邮箱验证码
     * @param captcha
     * @return
     * @throws Exception
     */
    @PostMapping("/getCode")
    public R getCode(@RequestBody @Validated Captcha captcha) throws Exception {
        if (!loginService.getCode(captcha)) {
            return R.error().message("验证码错误");
        }
        return R.ok().message("发送成功,请耐心等待");
    }


    /**
     * 检测邮箱验证码
     * @param email
     * @param code
     * @return
     */
    @PostMapping("/check")
    public R check(String email,Integer code){
        loginService.checkCode(email,code);
        return R.ok().message("验证成功");
    }

    /**
     * 注册
     * @param registerVO
     * @return
     * @throws Exception
     */
    @PostMapping("/register")
    public R register(@RequestBody @Validated RegisterVO registerVO) throws Exception {
        if (!loginService.register(registerVO)) {
            return R.error().message("注册失败,验证码错误");
        }
        return R.ok().message("注册成功");
    }

    /**
     * 找回密码
     * @param findPWVO
     * @return
     */
    @PostMapping("/findPassword")
    public R findPassword(@RequestBody @Validated FindPWVO findPWVO, HttpServletResponse response){
        final Boolean b = loginService.findPassword(findPWVO);
        return R.ok().message(b ? "修改成功" : "修改失败,验证码不正确");
    }


}
