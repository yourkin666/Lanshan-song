package com.example.lanshansong.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.lanshansong.Entity.user.Favorites;
import com.example.lanshansong.Entity.user.User;
import com.example.lanshansong.Entity.vo.FindPWVO;
import com.example.lanshansong.Entity.vo.RegisterVO;
import com.example.lanshansong.Entity.vo.UserVO;
import com.example.lanshansong.constant.RedisConstant;
import com.example.lanshansong.exception.BaseException;
import com.example.lanshansong.mapper.user.UserMapper;
import com.example.lanshansong.service.user.FavoritesService;
import com.example.lanshansong.service.user.FollowService;
import com.example.lanshansong.service.user.UserService;
import com.example.lanshansong.util.RedisCacheUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

import static com.example.lanshansong.constant.RedisConstant.USER_SEARCH_HISTORY_TIME;

/**
 * @author yourkin666
 * @date 2024/10/29/12:16
 * @description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Autowired
    private FavoritesService favoritesService;
    @Autowired
    private FollowService followService;
    @Override
    public boolean register(RegisterVO registerVO) throws Exception {

        // 邮箱是否存在
        final int count = count(new LambdaQueryWrapper<User>().eq(User::getEmail, registerVO.getEmail()));
        if (count == 1){
            throw new BaseException("邮箱已被注册");
        }
        final String code = registerVO.getCode();
        final Object o = redisCacheUtil.get(RedisConstant.EMAIL_CODE + registerVO.getEmail());
        if (o == null){
            throw new BaseException("验证码为空");
        }
        if (!code.equals(o)){
            return false;
        }

        final User user = new User();
        user.setNickName(registerVO.getNickName());
        user.setEmail(registerVO.getEmail());
        user.setDescription("喝喝");
        user.setPassword(registerVO.getPassword());
        save(user);

        // 创建默认收藏夹
        final Favorites favorites = new Favorites();
        favorites.setUserId(user.getId());
        favorites.setName("默认收藏夹");
        favoritesService.save(favorites);

        // 这里如果单独抽出一个用户配置表就好了,但是没有必要再搞个表
        user.setDefaultFavoritesId(favorites.getId());
        updateById(user);
        return true;
    }

    @Override
    public Boolean findPassword(FindPWVO findPWVO) {

        // 从redis中取出
        final Object o = redisCacheUtil.get(RedisConstant.EMAIL_CODE + findPWVO.getEmail());
        if (o==null){
            return false;
        }
        // 校验
        if (Integer.parseInt(o.toString()) != findPWVO.getCode()){
            return false;
        }
        // 修改
        final User user = new User();
        user.setEmail(findPWVO.getEmail());
        user.setPassword(findPWVO.getNewPassword());
        update(user,new UpdateWrapper<User>().lambda().set(User::getPassword,findPWVO.getNewPassword()).eq(User::getEmail,findPWVO.getEmail()));
        return true;
    }
    @Override
    @Async
    public void addSearchHistory(Long userId, String search) {
        if (userId!=null){
            redisCacheUtil.zadd(RedisConstant.USER_SEARCH_HISTORY+userId,new Date().getTime(),search,USER_SEARCH_HISTORY_TIME);
        }
    }
    @Override
    public UserVO getInfo(Long userId){

        final User user = getById(userId);
        if (ObjectUtils.isEmpty(user)){
            return new UserVO();
        }
        final UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);

        // 查出关注数量
        final long followCount = followService.getFollowCount(userId);

        // 查出粉丝数量
        final long fansCount = followService.getFansCount(userId);
        userVO.setFollow(followCount);
        userVO.setFans(fansCount);
        return userVO;
    }

}
