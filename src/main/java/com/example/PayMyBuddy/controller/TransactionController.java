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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TransactionController {
    private static final Logger logger = LogManager.getLogger(TransactionController.class);

    private TransactionService transactionService;
    private UserService userService;
    private ConnectionService connectionService;

    @Autowired
    public void TransactionController(TransactionService transactionService, UserService userService,
                                      ConnectionService connectionService) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.connectionService = connectionService;
    }

    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
    public Transaction getTransactionById(@PathVariable int id) {
        Transaction transaction = transactionService.getTransaction(id);
        logger.info("Transaction info by Id " + transaction.toString());
        return transaction;
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public ModelAndView getAllTransactions(@AuthenticationPrincipal CustomUserDetails customUserDetails, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("transactionDto", new TransactionDto());
        modelAndView.addObject(model);
        int id = userService.findByEmail(customUserDetails.getUsername()).getId();
        List<Transaction> listOfTransactions = transactionService.getAllTransaction();
        List<TransactionDto> transactionsDtoList = transactionService.getTransactionList(listOfTransactions, id);
        List<Connection> listOfConnections = connectionService.getAllConnections();
        List<ConnectionDto> connectionDtoList = connectionService.getConnectionList(listOfConnections, id);
        modelAndView.addObject("transactionsDtoList", transactionsDtoList);
        modelAndView.addObject("connectionDtoList", connectionDtoList);
        modelAndView.setViewName("transaction");
        return modelAndView;
    }

    @PostMapping("/transactions")// Add a transaction into DB
    public ModelAndView addTransaction(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                       TransactionDto transactionDto, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("transactionDto", new TransactionDto());
        modelAndView.addObject(model);
        Transaction transaction = new Transaction();
        User user = userService.findByEmail(customUserDetails.getUsername());
        transaction.setUser(user);
        transaction.setReceiver(userService.getUser(transactionDto.getReceiverId()));
        transaction.setAmount(transactionDto.getAmount());
        transaction.setDescription(transactionDto.getDescription());
        double availBalance = transaction.getUser().getBalance();
        double transactionAmount = transaction.getAmount();
        if ((availBalance) >= (transactionAmount + (transactionAmount * 0.005))) {
            transactionService.addTransaction(transaction);
            user.setBalance((availBalance) - (transactionAmount + (transactionAmount * 0.005)));
            userService.updateUser(user);
            modelAndView.addObject("transactionDto", new TransactionDto());
            modelAndView.setViewName("redirect:/transactions");
        } else {
            modelAndView.setViewName("/transaction-error");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.DELETE)
    public Transaction deleteTransaction(@PathVariable("id") int id) {
        Transaction transaction = transactionService.deleteTransaction(id);
        logger.info("deleted Transaction info " + transaction.toString());
        return transaction;
    }

}
