package com.jlink.service;

import com.jlink.entity.User;

public interface UserService {

    boolean saveUser(User user);

    User getUserByUsername(String username);

    User getUserById(Integer userId);

    boolean updateUser(User user);
}
