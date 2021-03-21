package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.dao.UserDao;
import com.example.PayMyBuddy.dto.UserDto;
import com.example.PayMyBuddy.model.Account;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.service.AccountService;
import com.example.PayMyBuddy.service.CustomUserDetails;
import com.example.PayMyBuddy.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @InjectMocks
    private UserController userController = new UserController();

    @Mock
    UserService userService;
    @Mock
    AccountService accountService;

    CustomUserDetails customUserDetails;
    UserDto userDto = new UserDto();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        User userid1 = new User();
        userid1.setId(1);
        userid1.setUserName("amy");
        userid1.setPassword("123456");
        userid1.setEmail("amy@gmail.com");
        userid1.setBalance(10.00);
        userid1.setCurrency("USD");
        User userid2 = new User();
        userid2.setId(2);
        userid2.setUserName("jamy");
        userid2.setPassword("123456");
        userid2.setEmail("jamy@gmail.com");
        userid2.setBalance(100.00);
        userid2.setCurrency("USD");
        List <User> list = new ArrayList<>();
        list.add(userid1);
        list.add(userid2);
        userDto.setCurrency("USD");
        userDto.setUserId(1);
        userDto.setUserName("amy");
        userDto.setPassword("123456");
        userDto.setEmail("amy@gmail.com");
        userDto.setAccountNo("12121212");
        userDto.setBankName("AAA");
        userDto.setBalance(120.00);
        Account account = new Account();
        customUserDetails = new CustomUserDetails(userid1);
        Mockito.when(userService.getUser(1)).thenReturn(userid1);
        Mockito.when(userService.findByEmail("amy@gmail.com")).thenReturn(userid1);
        Mockito.when(userService.getAllUsers()).thenReturn(list);
        Mockito.when(userService.deleteUser(2)).thenReturn(userid2);
        Mockito.when(userService.mapUserDtotoUser(userDto)).thenReturn(userid1);
        Mockito.when(accountService.mapUserDtotoAccount(userDto)).thenReturn(account);
    }

    @Test
    void getUserById() {
        User user = userController.getUserById(1);
        assertEquals( "amy",user.getUserName());
    }

    @Test
    void getUserByEmail() {
        User user = userController.getUserByEmail("amy@gmail.com");
        assertEquals("amy@gmail.com",user.getEmail());
    }

    @Test
    void getAllUsers() {
        List <User> userList = userController.getAllUsers();
        assertEquals("amy@gmail.com",userList.get(0).getEmail());
    }

    @Test
    void deleteUser() {
        User user = userController.deleteUser(2);
        assertEquals(2,user.getId());
    }

    @Test
    void processRegister() {
        ModelAndView modelAndView = userController.processRegister(userDto);
        assertEquals("register_success",modelAndView.getViewName());
    }

    @Test
    void updateUser() {
        ModelAndView modelAndView = userController.updateUser(customUserDetails,userDto);
        assertEquals("add_money_success",modelAndView.getViewName());
    }
}