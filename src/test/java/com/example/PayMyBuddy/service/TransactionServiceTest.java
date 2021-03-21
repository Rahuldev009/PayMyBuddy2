package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.dao.ConnectionDao;
import com.example.PayMyBuddy.dao.TransactionDao;
import com.example.PayMyBuddy.dto.TransactionDto;
import com.example.PayMyBuddy.model.Transaction;
import com.example.PayMyBuddy.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService = new TransactionService();

    @Mock
    TransactionDao transactionDao;

    @Test
    void getTransactionList() {
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
        List<Transaction> list = new ArrayList<>();
        list.add(transaction1);
        list.add(transaction2);
        List <TransactionDto> transactionList = transactionService.getTransactionList(list,1);
        assertEquals( "drinks",transactionList.get(0).getDescription());
    }
}