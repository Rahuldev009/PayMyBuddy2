package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.dto.ConnectionDto;
import com.example.PayMyBuddy.dto.TransactionDto;
import com.example.PayMyBuddy.model.Connection;
import com.example.PayMyBuddy.model.Transaction;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.service.ConnectionService;
import com.example.PayMyBuddy.service.CustomUserDetails;
import com.example.PayMyBuddy.service.TransactionService;
import com.example.PayMyBuddy.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionControllerTest {
    @Mock
    TransactionService transactionService;
    @Mock
    UserService userService;
    @Mock
    ConnectionService connectionService;
    @Mock
    Model model;


    CustomUserDetails customUserDetails;
    TransactionDto transactionDto = new TransactionDto();
    ConnectionDto connectionDto = new ConnectionDto();

    @InjectMocks
    private TransactionController transactionController = new TransactionController();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        User userid1 = new User();
        Connection connection1 = new Connection();
        Connection connection2 = new Connection();
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
        User userid3 = new User();
        userid3.setId(3);
        userid3.setUserName("jimmy");
        userid3.setPassword("123456");
        userid3.setEmail("jimmy@gmail.com");
        userid3.setBalance(100.00);
        userid3.setCurrency("USD");
        User userid4 = new User();
        userid4.setId(4);
        userid4.setUserName("ann");
        userid4.setPassword("123456");
        userid4.setEmail("ann@gmail.com");
        userid4.setBalance(50.00);
        userid4.setCurrency("USD");
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
        List<Transaction> list = new ArrayList<>();
        list.add(transaction1);
        list.add(transaction2);
        connection1.setId(1);
        connection1.setUser(userid1);
        connection1.setFriend(userid2);
        connection2.setId(2);
        connection2.setUser(userid1);
        connection2.setFriend(userid3);
        List<Connection> cList = new ArrayList<>();
        cList.add(connection1);
        cList.add(connection2);
        transactionDto.setCurrency("USD");
        transactionDto.setAmount(5.00);
        transactionDto.setDescription("grocery");
        transactionDto.setName("amy");
        transactionDto.setUserId(1);
        transactionDto.setReceiverId(2);
        connectionDto.setName("ann");
        connectionDto.setFriendId(1);
        connectionDto.setUserId(4);
        connectionDto.setEmail("amy@gmail.com");
        connectionDto.setFriendName("amy");
        customUserDetails = new CustomUserDetails(userid1);
        Mockito.when(userService.findByEmail("amy@gmail.com")).thenReturn(userid1);
        Mockito.when(transactionService.getTransaction(1)).thenReturn(transaction1);
        Mockito.when(transactionService.getAllTransaction()).thenReturn(list);
        Mockito.when(transactionService.deleteTransaction(1)).thenReturn(transaction1);
        Mockito.when(connectionService.getAllConnections()).thenReturn(cList);
    }


    @Test
    void getTransactionById() {
        Transaction transaction = transactionController.getTransactionById(1);
        assertEquals("food", transaction.getDescription());
    }

    @Test
    void getAllTransactions() {
        ModelAndView modelAndView = transactionController.getAllTransactions(customUserDetails, model);
        assertEquals("transaction", modelAndView.getViewName());
    }


    @Test
    void deleteTransaction() {
        Transaction transaction = transactionController.deleteTransaction(1);
        assertEquals("food", transaction.getDescription());
    }

    @Test
    void addTransaction() {
        ModelAndView modelAndView = transactionController.addTransaction(customUserDetails, transactionDto, model);
        assertEquals("redirect:/transactions", modelAndView.getViewName());
    }
}