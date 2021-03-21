package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.controller.UserController;
import com.example.PayMyBuddy.dao.AccountDao;
import com.example.PayMyBuddy.dto.UserDto;
import com.example.PayMyBuddy.model.Account;
import com.example.PayMyBuddy.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @InjectMocks
    private AccountService accountService = new AccountService();

    @Mock
    AccountDao accountDao;


    @Test
    void mapUserDtotoAccount() {
        UserDto userDto = new UserDto();
        userDto.setEmail("amy@gmail.com");
        userDto.setAccountNo("11212212");
        userDto.setUserId(1);
        userDto.setBankName("AAA");
        userDto.setUserName("abc");
        userDto.setBalance(100.00);
        userDto.setCurrency("USD");
        userDto.setPassword("123456");
        Account account = accountService.mapUserDtotoAccount(userDto);
        assertEquals( "11212212",account.getAccountNo());
    }
}