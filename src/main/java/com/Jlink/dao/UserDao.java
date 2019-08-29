package com.Jlink.dao;

import com.Jlink.entity.User;

public interface UserDao {
    int saveUser(User user);

    User getUserByUsername(String username);

    User getUserById(Long userId);

    int updateUser(User user);
}
