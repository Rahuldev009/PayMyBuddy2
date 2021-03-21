package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.dto.ConnectionDto;
import com.example.PayMyBuddy.model.Connection;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.service.ConnectionService;
import com.example.PayMyBuddy.service.CustomUserDetails;
import com.example.PayMyBuddy.service.UserService;
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

class ConnectionControllerTest {

    @InjectMocks
    private ConnectionController connectionController = new ConnectionController();

    @Mock
    ConnectionService connectionService;
    @Mock
    UserService userService;


    CustomUserDetails customUserDetails;
    ConnectionDto connectionDto = new ConnectionDto();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
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
        User userid4 = new User();
        userid4.setId(4);
        userid4.setUserName("ann");
        userid4.setPassword("123456");
        userid4.setEmail("ann@gmail.com");
        userid4.setBalance(50.00);
        userid4.setCurrency("USD");
        connection1.setId(1);
        connection1.setUser(userid1);
        connection1.setFriend(userid2);
        connection2.setId(2);
        connection2.setUser(userid1);
        connection2.setFriend(userid3);
        List<Connection> list = new ArrayList<>();
        list.add(connection1);
        list.add(connection2);
        connectionDto.setName("ann");
        connectionDto.setFriendId(1);
        connectionDto.setUserId(4);
        connectionDto.setEmail("amy@gmail.com");
        connectionDto.setFriendName("amy");
        customUserDetails = new CustomUserDetails(userid1);
        Mockito.when(userService.getUser(1)).thenReturn(userid1);
        Mockito.when(userService.findByEmail("amy@gmail.com")).thenReturn(userid1);
        Mockito.when(connectionService.getConnection(1)).thenReturn(connection1);
        Mockito.when(connectionService.getAllConnections()).thenReturn(list);
        Mockito.when(connectionService.deleteConnection(1)).thenReturn(connection1);
    }


    @Test
    void getConnectionById() {
        Connection connection = connectionController.getConnectionById(1);
        assertEquals("jamy",connection.getFriend().getUserName());
    }

    @Test
    void getAllConnections() {
        List <Connection> connectionList = connectionController.getAllConnections();
        assertEquals("jimmy",connectionList.get(1).getFriend().getUserName());
    }

    @Test
    void deleteConnection() {
        Connection connection = connectionController.deleteConnection(1);
        assertEquals("jamy",connection.getFriend().getUserName());
    }

    @Test
    void addConnection() {
        ModelAndView modelAndView = connectionController.addConnection(customUserDetails,connectionDto);
        assertEquals("connection_fail",modelAndView.getViewName());
    }

}