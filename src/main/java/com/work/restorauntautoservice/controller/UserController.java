package com.work.restorauntautoservice.controller;

import com.work.restorauntautoservice.model.User;
import com.work.restorauntautoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user-create")
    @ResponseStatus(HttpStatus.CREATED)
    User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/user-edit/{userName}")
    User editUser(@PathVariable String userName, @RequestBody User user) {
        return userService.editUser(userName, user);
    }

    @GetMapping("/user-findAll")
    List<User> findAllUsers() {
        return userService.findAllUsers();
    }
}
