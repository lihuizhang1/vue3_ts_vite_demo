package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    public User loginUser(User user);
    public int signUp(User user);
    public User selectUser(User user);
}
