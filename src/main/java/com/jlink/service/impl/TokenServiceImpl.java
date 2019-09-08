package com.jlink.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jlink.entity.User;
import com.jlink.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(User user) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(user.getUserId()))
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
