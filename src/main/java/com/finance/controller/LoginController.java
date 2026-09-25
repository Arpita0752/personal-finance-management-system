package com.finance.controller;

import com.finance.model.User;
import com.finance.service.UserService;

public class LoginController {

    private UserService userService = new UserService();

    public User login(String email, String password) {
        return userService.loginUser(email, password);
    }
}