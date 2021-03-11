
package com.example.demo.util;

import com.auth0.jwt.JWT;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class TokenUtil {
    public static String getTokenUId() {
        String token = Objects.requireNonNull(getRequest()).getHeader("token");// 从 http 请求头中取出 token
        if(token==null){
            return "";
        }
        return JWT.decode(token).getAudience().get(0);
    }

    public static String getTokenUName() {
        String token = Objects.requireNonNull(getRequest()).getHeader("token");// 从 http 请求头中取出 token
        if(token==null){
            return "";
        }
        return JWT.decode(token).getAudience().get(1);
    }

    /**
     * 获取request
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}