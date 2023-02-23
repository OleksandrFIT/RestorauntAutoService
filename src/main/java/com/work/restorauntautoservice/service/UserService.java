package com.work.restorauntautoservice.service;

import com.work.restorauntautoservice.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User createUser(User user);
    User editUser(String userName, User user);
}
