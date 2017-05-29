package com.example.demo.service;

import com.example.demo.model.User;


public interface UserService {

    public User findByEmail(String email);
    public void saveUser(User user);

}
