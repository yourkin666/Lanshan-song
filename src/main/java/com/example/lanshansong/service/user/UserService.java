package com.example.lanshansong.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.lanshansong.Entity.user.User;
import com.example.lanshansong.Entity.vo.FindPWVO;
import com.example.lanshansong.Entity.vo.RegisterVO;

/**
 * @author yourkin666
 * @date 2024/10/29/12:15
 * @description
 */
public interface UserService extends IService<User> {
    /**
     * 注册
     * @param registerVO
     * @return
     * @throws Exception
     */
    boolean register(RegisterVO registerVO) throws Exception;

    /**
     * 找回密码
     * @param findPWVO
     * @return
     */
    Boolean findPassword(FindPWVO findPWVO);
}
