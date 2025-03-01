package com.tourPlanner.service;

import com.tourPlanner.dto.UserDTO;

public interface UserService {
    UserDTO findUserByUserNameAndPassword(String userName,String passsword);
}
