package com.tourPlanner.repository;

import com.tourPlanner.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByUserNameAndPassword(String userName,String password);


}
