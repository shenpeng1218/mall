package com.moss.impl;

import com.moss.bean.User;
import com.moss.mapper.UserDao;
import com.moss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public int insertUser() {
        User moss = new User(2, "moss");
        int id = userDao.insertUser(moss);

        User peter = new User(2, "peter");
        userDao.insertUser(peter);
        return id;
    }
}
