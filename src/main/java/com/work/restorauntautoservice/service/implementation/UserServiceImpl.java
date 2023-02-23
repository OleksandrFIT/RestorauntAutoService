package com.work.restorauntautoservice.service.implementation;

import com.work.restorauntautoservice.exception.UserNameNotFoundException;
import com.work.restorauntautoservice.model.User;
import com.work.restorauntautoservice.repository.UserRepository;
import com.work.restorauntautoservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = userRepository.findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                AuthorityUtils.createAuthorityList("USER"));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editUser(String userName,User user) {
        User userToUpdate = userRepository.findByUsername(userName);
        userToUpdate.setPassword(user.getPassword());
        return userToUpdate;
    }
}
