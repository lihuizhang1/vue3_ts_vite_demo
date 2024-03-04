package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User loginUser(User user) {
        return userMapper.loginUser(user);
    }
}
