package com.example.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.domian.Entity.Users;
import org.springframework.stereotype.Service;

import java.util.Date;

/***
 * token 发送token
* @Title: TokenService.java 
* @author MRC
 */
@Service("TokenService")
public class TokenService {

    public String getToken(Users user) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        
        token = JWT.create().withAudience(user.getUserId(),user.getUsername()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
