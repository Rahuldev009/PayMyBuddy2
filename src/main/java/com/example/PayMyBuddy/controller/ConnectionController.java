package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.dto.ConnectionDto;
import com.example.PayMyBuddy.model.Connection;
import com.example.PayMyBuddy.service.ConnectionService;
import com.example.PayMyBuddy.service.CustomUserDetails;
import com.example.PayMyBuddy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ConnectionController {
    private static final Logger logger = LogManager.getLogger(ConnectionController.class);

    private ConnectionService connectionService;
    private UserService userService;

    @Autowired
    public void ConnectionController(ConnectionService connectionService, UserService userService) {
        this.connectionService = connectionService;
        this.userService = userService;
    }

    @RequestMapping(value = "/connections/{id}", method = RequestMethod.GET)
    public Connection getConnectionById(@PathVariable int id) {
        Connection connection = connectionService.getConnection(id);
        logger.info("Connection info by Id" + connection.toString());
        return connection;
    }

    @RequestMapping(value = "/connections", method = RequestMethod.GET)
    public List<Connection> getAllConnections() {
        List<Connection> listOfConnections = connectionService.getAllConnections();
        logger.info("All connection info " + listOfConnections.toString());
        return listOfConnections;
    }

    @PostMapping("/connections")// Add a connection into DB
    public ModelAndView addConnection(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                      ConnectionDto connectionDto) {
        ModelAndView modelAndView = new ModelAndView();
        Connection connection = new Connection();
        connection.setUser(userService.findByEmail(customUserDetails.getUsername()));
        connection.setFriend(userService.findByEmail(connectionDto.getEmail()));
        logger.info("user id " + connection.getUser().getId());
        logger.info("friend id " + connection.getFriend().getId());
        if (connection.getUser().getId() == connection.getFriend().getId()) {
            logger.debug("trying to add someone as his own friend");
            modelAndView.setViewName("connection_fail");
            return modelAndView;
        }
        try {
            logger.debug("adding friend");
            connectionService.addConnection(connection);
        } catch (Exception e) {
            logger.debug("if the person is already added as friend");
            modelAndView.setViewName("connection_fail_error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/connections/{id}", method = RequestMethod.DELETE)
    public Connection deleteConnection(@PathVariable("id") int id) {
        Connection connection = connectionService.deleteConnection(id);
        logger.info("deleted connection info " + connection.toString());
        return connection;
    }

}
