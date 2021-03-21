package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.dao.AccountDao;
import com.example.PayMyBuddy.dao.UserDao;
import com.example.PayMyBuddy.dto.UserDto;
import com.example.PayMyBuddy.model.Account;
import com.example.PayMyBuddy.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService = new UserService();

    @Mock
    UserDao userDao;

    @Test
    void mapUserDtotoUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail("amy@gmail.com");
        userDto.setAccountNo("11212212");
        userDto.setUserId(1);
        userDto.setBankName("AAA");
        userDto.setUserName("abc");
        userDto.setBalance(100.00);
        userDto.setCurrency("USD");
        userDto.setPassword("123456");
        User user = userService.mapUserDtotoUser(userDto);
        assertEquals( "abc",user.getUserName());
    }
}