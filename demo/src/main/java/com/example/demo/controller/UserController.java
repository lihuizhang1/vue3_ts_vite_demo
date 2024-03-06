package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/loginUser")
    @ResponseBody
    public User loginUser(@RequestBody User user) {
        return userService.loginUser(user);
    }

    @PostMapping("/signUp")
    @ResponseBody
    public int signUp(@RequestBody User user) {
        return userService.signUp(user);
    }
}
