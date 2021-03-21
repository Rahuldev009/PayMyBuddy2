package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.dto.TransactionDto;
import com.example.PayMyBuddy.model.Connection;
import com.example.PayMyBuddy.model.Transaction;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.service.ConnectionService;
import com.example.PayMyBuddy.service.CustomUserDetails;
import com.example.PayMyBuddy.service.TransactionService;
import com.example.PayMyBuddy.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionControllerTest {
    @InjectMocks
    private TransactionController transactionController = new TransactionController();

    @Mock
    TransactionService transactionService;
    @Mock
    UserService userService;


    CustomUserDetails customUserDetails;
    TransactionDto transactionDto= new TransactionDto();

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
        Transaction transaction1 = new Transaction();
        transaction1.setId(1);
        transaction1.setUser(userid1);
        transaction1.setReceiver(userid2);
        transaction1.setDescription("food");
        transaction1.setAmount(100.10);
        Transaction transaction2 = new Transaction();
        transaction2.setId(2);
        transaction2.setUser(userid1);
        transaction2.setReceiver(userid2);
        transaction2.setDescription("drinks");
        transaction2.setAmount(15.10);
        List <Transaction> list = new ArrayList<>();
        list.add(transaction1);
        list.add(transaction2);
        transactionDto.setCurrency("USD");
        transactionDto.setAmount(12.00);
        transactionDto.setDescription("grocery");
        transactionDto.setName("amy");
        transactionDto.setUserId(1);
        transactionDto.setReceiverId(2);
        customUserDetails = new CustomUserDetails(userid1);
        Mockito.when(userService.findByEmail("amy@gmail.com")).thenReturn(userid1);
        Mockito.when(transactionService.getTransaction(1)).thenReturn(transaction1);
        Mockito.when(transactionService.getAllTransaction()).thenReturn(list);
        Mockito.when(transactionService.deleteTransaction(1)).thenReturn(transaction1);
    }


    @Test
    void getTransactionById() {
        Transaction transaction = transactionController.getTransactionById(1);
        assertEquals("food",transaction.getDescription());
    }

    @Test
    void getAllTransactions() {
        List <Transaction> transactionList = transactionController.getAllTransactions();
        assertEquals("drinks",transactionList.get(1).getDescription());
    }


    @Test
    void deleteTransaction() {
        Transaction transaction = transactionController.deleteTransaction(1);
        assertEquals("food",transaction.getDescription());
    }

    @Test
    void addTransaction() {
        ModelAndView modelAndView = transactionController.addTransaction(customUserDetails,transactionDto);
        assertEquals("transaction_fail",modelAndView.getViewName());
    }
}