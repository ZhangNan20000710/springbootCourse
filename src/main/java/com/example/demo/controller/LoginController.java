package com.example.demo.controller;


import cn.hutool.core.util.RandomUtil;
import com.example.demo.dao.IUserDao;
import com.example.demo.domian.Entity.Users;
import com.example.demo.domian.Information.Message;
import com.example.demo.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController()
@Api(tags = "登录接口")
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    IUserDao userDao;

    @Autowired
    StringRedisTemplate template;

    @Autowired
    TokenService tokenService;

    @ApiOperation("账号密码登录")
    @PostMapping("/sign")
    public Message login(String username,String password){
        Users user = userDao.
                findByUsernameAndPassword(username, password);
        if(user!=null){
            user.setLoginTime(new Date());
            //给客户的发还token
            String token = tokenService.getToken(user);
            return Message.success("登录成功")
                    .add("user",user)
                    .add("token",token);
        }else {
            return Message.error(username+"用户名或密码错误");
        }
    }

    @ApiOperation("验证手机登录")
    @PostMapping("/phoneSign")
    public Message phoneSign(String phoneNumber,String VCode){
        Users user = userDao.findByPhoneNumber(phoneNumber);
        if(user!=null){
            if(VCode.equals(template.opsForValue()
                    .get(user.getPhoneNumber()))){
                String token = tokenService.getToken(user);
                return Message.success("登录成功")
                        .add("user",user)
                        .add("token",token);
            }else {
                return Message.error("验证码无效");
            }
        }else {
            return Message.error("手机号码错误");
        }
    }

    @ApiOperation("管理员登录")
    @PostMapping("/adminSign")
    public Message adminSign(String username,String password){
        if("admin".equals(username)&&"admin".equals(password)){
            Users user = new Users();
            user.setUsername(username);
            user.setUserId("");
            user.setPassword(password);
            user.setNickname("管理员");
            user.setHeadPortrait("/images/portrait/admin/timg.jpg");
            String token = tokenService.getToken(user);
            return Message.success("登录成功")
                    .add("user",user)
                    .add("token",token);
        }else {
            return Message.error("用户或密码错误");
        }
    }

    @ApiOperation("发送验证码")
    @PostMapping("/checkVCode")
    public Message sendVCode(String phoneNumber){
        /*随机生成验证码*/
        int i = RandomUtil.randomInt(100000, 1000000);
        System.out.println(phoneNumber);
        /*判断手机号码是否存在*/
        if(userDao.findByPhoneNumber(phoneNumber)==null){
            return Message.error("手机号码没有注册");
        }
        System.out.println(i);
        template.opsForValue()
                .set(phoneNumber,String.valueOf(i));
        return Message.success("发送成功");
    }

}
