package com.Jlink.service.impl;

import com.Jlink.dao.UserDao;
import com.Jlink.entity.User;
import com.Jlink.service.UserService;
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
    public User getUserById(Long userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user) > 0;
    }
}
