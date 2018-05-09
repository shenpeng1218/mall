package com.moss.service;

import com.moss.bean.User;

public interface UserService {

    public User getUserById(int id);

    public int insertUser();
}
