package com.example.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.bean.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("TokenService")
public class TokenService {
    public String getToken(User user) {
        String token="";
        token= JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*40))//设置过期时间 单位:毫秒
                .withAudience(String.valueOf(user.getId()))// 将 user id 保存到 token 里面
                .sign(Algorithm.HMAC256(user.getPassword()));// 以 password 作为 token 的密钥
        //获取过期时间
        DecodedJWT jwt = JWT.decode(token);
        Date expiresAt = jwt.getExpiresAt();
        System.out.println("过期时间："+expiresAt);
        return token;
    }
}
