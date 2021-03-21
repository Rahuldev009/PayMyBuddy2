package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.model.Account;
import com.example.PayMyBuddy.model.Transaction;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.service.AccountService;
import com.example.PayMyBuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountControllerTest {

    @InjectMocks
    private AccountController accountController = new AccountController();

    @Mock
    AccountService accountService;


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
        Account account1 = new Account();
        account1.setAccountNo("111122222");
        account1.setUser(userid1);
        account1.setBank("AAA");
        account1.setId(1);
        Account account2 = new Account();
        account2.setAccountNo("111155555");
        account2.setUser(userid2);
        account2.setBank("BBB");
        account2.setId(2);
        List <Account> list = new ArrayList<>();
        list.add(account1);
        list.add(account2);
        Mockito.when(accountService.getAccount(1)).thenReturn(account1);
        Mockito.when(accountService.getAllAccounts()).thenReturn(list);
        Mockito.when(accountService.deleteAccount(1)).thenReturn(account1);
    }

    @Test
    void getAccountById() {
        Account account = accountController.getAccountById(1);
        assertEquals( "amy",account.getUser().getUserName());
    }

    @Test
    void getAllAccounts() {
        List <Account> accountList = accountController.getAllAccounts();
        assertEquals( "amy",accountList.get(0).getUser().getUserName());
    }

    @Test
    void deleteAccount() {
        Account account = accountController.deleteAccount(1);
        assertEquals( "amy",account.getUser().getUserName());
    }
}