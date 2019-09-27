package com.sunshine.hardware.service;

import com.sunshine.hardware.dao.UserDao;
import com.sunshine.hardware.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User login(String username, String password){
        return userDao.login(username, password);
    }
}
