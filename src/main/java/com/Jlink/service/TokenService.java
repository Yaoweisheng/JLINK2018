package com.Jlink.service;

import com.Jlink.entity.User;

public interface TokenService {
    String getToken(User user);
}
