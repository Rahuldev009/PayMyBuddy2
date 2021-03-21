package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.dao.UserDao;
import com.example.PayMyBuddy.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

class CustomUserDetailsServiceTest {
    @InjectMocks
    private CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService();

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername() {
        User userid1 = new User();
        userid1.setId(1);
        userid1.setUserName("amy");
        userid1.setPassword("123456");
        userid1.setEmail("amy@gmail.com");
        userid1.setBalance(10.00);
        userid1.setCurrency("USD");
        Mockito.when(userService.findByEmail("amy@gmail.com")).thenReturn(userid1);
        CustomUserDetails customUserDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername("amy@gmail.com");
        assertEquals( "amy@gmail.com",customUserDetails.getUsername());
    }
}