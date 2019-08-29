package com.Jlink.service;

import com.Jlink.entity.User;

public interface UserService {

    boolean saveUser(User user);

    User getUserByUsername(String username);

    User getUserById(Long userId);

    boolean updateUser(User user);
}
