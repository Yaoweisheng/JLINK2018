package com.jlink.service.impl;

import com.jlink.dao.UserDao;
import com.jlink.entity.User;
import com.jlink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean saveUser(User user) {
        return userDao.saveUser(user) > 0;
    }

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user) > 0;
    }
}
