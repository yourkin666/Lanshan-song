package com.example.lanshansong.config;

import com.example.lanshansong.Entity.user.User;
import com.example.lanshansong.holder.UserHolder;
import com.example.lanshansong.service.user.UserService;
import com.example.lanshansong.util.JwtUtils;
import com.example.lanshansong.util.R;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yourkin666
 * @date 2024/10/30/1:37
 * @description
 */

@Component
public class Interceptor implements HandlerInterceptor {

    private ObjectMapper objectMapper = new ObjectMapper();

    private UserService userService;

    public Interceptor(UserService userService){
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equals("OPTIONS")){
            return true;
        }

        if (!JwtUtils.checkToken(request)) {
            response(R.error().message("请登录后再操作"),response);
            return false;
        }

        final Long userId = JwtUtils.getUserId(request);
        final User user = userService.getById(userId);
        if (ObjectUtils.isEmpty(user)){
            response(R.error().message("用户不存在"),response);
            return false;
        }
        UserHolder.set(userId);
        return true;
    }

    private boolean response(R r, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(objectMapper.writeValueAsString(r));
        response.getWriter().flush();
        return false;
    }
}
