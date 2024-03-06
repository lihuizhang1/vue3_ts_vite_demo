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

    @Override
    public int signUp(User user) {
        try {
            User signUpUser = userMapper.selectUser(user);
            if(signUpUser == null){
                return userMapper.signUpUser(user);
            }else{
                return 2;
            }
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public User selectUser(User user) {
        return userMapper.selectUser(user);
    }
}
