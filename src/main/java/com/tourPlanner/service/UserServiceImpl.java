package com.tourPlanner.service;

import com.tourPlanner.dto.UserDTO;
import com.tourPlanner.entity.User;
import com.tourPlanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO findUserByUserNameAndPassword(String userName,String password) {
        User tempUser =null;
        List<User> tempUsers = userRepository.findByUserNameAndPassword(userName,password);

        if(tempUsers ==null || tempUsers.size() == 0){
            System.out.println(tempUsers);
            System.out.println(tempUser);
            return null;


            //tempUser = tempUsers.get(0);
        };
        tempUser = tempUsers.get(0);
        return  new UserDTO(tempUser.getId(),tempUser.getUserName(),tempUser.getPassword(),tempUser.getUserType());

    }
}
