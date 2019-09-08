package com.jlink.service;

import com.jlink.entity.User;

public interface TokenService {
    String getToken(User user);
}
