package com.work.restorauntautoservice.repository;

import com.work.restorauntautoservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
