package com.example.demo.config;

import cn.hutool.json.JSONUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.annotation.AdminLoginToken;
import com.example.demo.annotation.PassToken;
import com.example.demo.annotation.UserLoginToken;
import com.example.demo.dao.IUserDao;
import com.example.demo.domian.Entity.Users;
import com.example.demo.domian.Information.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 拦截器
 */

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    IUserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }

        //检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(UserLoginToken.class)) {
            response.setCharacterEncoding("utf-8");
            response.setContentType("*/*; charset=utf-8");
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    response.getWriter().print(JSONUtil.parseObj(Message.error("没有登录，请登录----0"), false));
                    return false;
                }
                // 获取 token 中的 user id
                String username = "";
                try {
                    //获取用户名
                    username = JWT.decode(token).getAudience().get(1);
                } catch (JWTDecodeException j) {
                    response.getWriter().print(JSONUtil.parseObj(Message.error("身份验证过期，请登录----1"), false));
                    return false;
                }
                Users user = userDao.findByUsername(username);
                if (user == null) {
                    response.getWriter().print(JSONUtil.parseObj(Message.error("用户不存在，请重新登录----2"), false));
                    return false;
                }
                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    response.getWriter().print(JSONUtil.parseObj(Message.error("身份验证过期，请登录----3"), false));
                    return false;
                }
                return true;
            }
        }

        //检查有没有需要管理员权限的注解
        if(method.isAnnotationPresent(AdminLoginToken.class)){
            response.setCharacterEncoding("utf-8");
            response.setContentType("*/*; charset=utf-8");
            AdminLoginToken userLoginToken = method.getAnnotation(AdminLoginToken.class);
            if (userLoginToken.required()) {
                // 执行认证
                if (token == null) {
                    response.getWriter().print(JSONUtil.parseObj(Message.error("没有登录，请登录----0"), false));
                    return false;
                }
                String username = JWT.decode(token).getAudience().get(1);
                if(!"admin".equals(username)){
                    response.getWriter().print(JSONUtil.parseObj(Message.error("请登录管理员账号"), false));
                    return false;
                }

                // 验证 token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("admin")).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    response.getWriter().print(JSONUtil.parseObj(Message.error("身份验证过期，请登录----3"), false));
                    return false;
                }
                return true;
            }
        }

        return true;
    }
}
