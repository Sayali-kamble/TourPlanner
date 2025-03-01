package com.tourPlanner.service;

import com.tourPlanner.dto.UserDTO;
import com.tourPlanner.entity.User;
import com.tourPlanner.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito with JUnit 5
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository; // Mocking repository

    @InjectMocks
    private UserServiceImpl userService; // Injecting the mock

    private User testUser;

    @BeforeEach
    void setUp() {
        // Create a test user with a String ID
        testUser = new User();
        testUser.setId("U123");  // Setting ID as String
        testUser.setUserName("testUser");
        testUser.setPassword("testPass");
        testUser.setUserType("ADMIN");
    }

    @Test
    void testFindUserByUserNameAndPassword_UserExists() {
        // Mock repository response
        when(userRepository.findByUserNameAndPassword("testUser", "testPass"))
                .thenReturn(List.of(testUser));

        // Call the service method
        UserDTO result = userService.findUserByUserNameAndPassword("testUser", "testPass");

        // Assertions
        assertNotNull(result);
        assertEquals("U123", result.getId()); // Now returning ID as String
        assertEquals("testUser", result.getUserName());
        assertEquals("testPass", result.getPassword());
        assertEquals("ADMIN", result.getUserType());

        // Verify that repository method was called once
        verify(userRepository, times(1)).findByUserNameAndPassword("testUser", "testPass");
    }

    @Test
    void testFindUserByUserNameAndPassword_UserNotFound() {
        // Mock repository returning an empty list
        when(userRepository.findByUserNameAndPassword("wrongUser", "wrongPass"))
                .thenReturn(Collections.emptyList());

        // Call the service method
        UserDTO result = userService.findUserByUserNameAndPassword("wrongUser", "wrongPass");

        // Assertions
        assertNull(result);

        // Verify interaction
        verify(userRepository, times(1)).findByUserNameAndPassword("wrongUser", "wrongPass");
    }
    
    @Test
    void testFindUserByUserNameAndPassword_NullListReturned() {
        // Mock repository returning null
        when(userRepository.findByUserNameAndPassword("testUser", "testPass"))
                .thenReturn(null);

        // Call the service method
        UserDTO result = userService.findUserByUserNameAndPassword("testUser", "testPass");

        // Assertions
        assertNull(result);

        // Verify interaction
        verify(userRepository, times(1)).findByUserNameAndPassword("testUser", "testPass");
    }
}
