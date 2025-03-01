package com.tourPlanner.controller;

import com.tourPlanner.dto.TourDTO;
import com.tourPlanner.dto.UserDTO;
import com.tourPlanner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-api")
public class UserRestController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/users")
    public UserDTO findByUserNameAndPassword(@RequestBody UserDTO userDTO) {
        System.out.println("Came here");
        System.out.println(userDTO);
        if (userService.findUserByUserNameAndPassword(userDTO.getUserName(),userDTO.getPassword()) != null) {
            System.out.println("Returing");
            System.out.println(userService.findUserByUserNameAndPassword(userDTO.getUserName(),userDTO.getPassword()));
            return userService.findUserByUserNameAndPassword(userDTO.getUserName(),userDTO.getPassword());
        } else {
            throw new RuntimeException("User  not found " + userDTO.getUserName());

        }
    }
}
