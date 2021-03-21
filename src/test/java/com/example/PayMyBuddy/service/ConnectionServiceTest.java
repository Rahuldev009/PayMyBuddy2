package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.dao.AccountDao;
import com.example.PayMyBuddy.dao.ConnectionDao;
import com.example.PayMyBuddy.dto.ConnectionDto;
import com.example.PayMyBuddy.model.Connection;
import com.example.PayMyBuddy.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionServiceTest {

    @InjectMocks
    private ConnectionService connectionService = new ConnectionService();

    @Mock
    ConnectionDao connectionDao;

    @Test
    void getConnectionList() {
        Connection connection1 = new Connection();
        Connection connection2 = new Connection();
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
        User userid3 = new User();
        userid3.setId(3);
        userid3.setUserName("jimmy");
        userid3.setPassword("123456");
        userid3.setEmail("jimmy@gmail.com");
        userid3.setBalance(100.00);
        userid3.setCurrency("USD");
        connection1.setId(1);
        connection1.setUser(userid1);
        connection1.setFriend(userid2);
        connection2.setId(2);
        connection2.setUser(userid1);
        connection2.setFriend(userid3);
        List<Connection> list = new ArrayList<>();
        list.add(connection1);
        list.add(connection2);
        List <ConnectionDto> connectionList = connectionService.getConnectionList(list,1);
        assertEquals("jamy",connectionList.get(0).getName());
    }
}