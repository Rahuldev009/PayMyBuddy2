package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.dto.ConnectionDto;
import com.example.PayMyBuddy.dto.TransactionDto;
import com.example.PayMyBuddy.dto.UserDto;
import com.example.PayMyBuddy.model.Connection;
import com.example.PayMyBuddy.model.Transaction;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.service.ConnectionService;
import com.example.PayMyBuddy.service.CustomUserDetails;
import com.example.PayMyBuddy.service.TransactionService;
import com.example.PayMyBuddy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class LoginController {
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    private TransactionService transactionService;
    private ConnectionService connectionService;
    private UserService userService;

    @Autowired
    public void LoginController(TransactionService transactionService, ConnectionService connectionService,
                                UserService userService) {
        this.transactionService = transactionService;
        this.connectionService = connectionService;
        this.userService = userService;
    }

    @RequestMapping(value = "/index")
    public String viewHomePage() {
        logger.info("returning home page");
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "signup_form";
    }

    @GetMapping("/landingPage")
    public String listUsers(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        User user = userService.findByEmail(customUserDetails.getUsername());
        int id = user.getId();
        List<Transaction> allTransaction = transactionService.getAllTransaction();
        List<TransactionDto> transactionsDtoList = transactionService.getTransactionList(allTransaction, id);
        List<Connection> allConnection = connectionService.getAllConnections();
        List<ConnectionDto> connectionDtoList = connectionService.getConnectionList(allConnection, id);
        model.addAttribute("connectionDtoList", connectionDtoList);
        model.addAttribute("transactionsDtoList", transactionsDtoList);
        model.addAttribute("transactionDto", new TransactionDto());
        model.addAttribute("connectionDto", new ConnectionDto());
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("moneyAvailable", userService.findByEmail
                (customUserDetails.getUsername()).getBalance());
        return "landingPage";
    }

}
