package com.jlink.dao;

import com.jlink.entity.User;

public interface UserDao {
    int saveUser(User user);

    User getUserByUsername(String name);

    User getUserById(Integer userId);

    int updateUser(User user);
}
