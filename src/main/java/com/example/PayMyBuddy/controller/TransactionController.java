package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.dto.TransactionDto;
import com.example.PayMyBuddy.model.Transaction;
import com.example.PayMyBuddy.model.User;
import com.example.PayMyBuddy.service.CustomUserDetails;
import com.example.PayMyBuddy.service.TransactionService;
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
public class TransactionController {
    private static final Logger logger = LogManager.getLogger(TransactionController.class);

    private TransactionService transactionService;
    private UserService userService;

    @Autowired
    public void TransactionController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @RequestMapping(value = "/transactions/{id}", method = RequestMethod.GET)
    public Transaction getTransactionById(@PathVariable int id) {
        Transaction transaction = transactionService.getTransaction(id);
        logger.info("Transaction info by Id " + transaction.toString());
        return transaction;
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public List<Transaction> getAllTransactions() {
        List<Transaction> listOfTransactions = transactionService.getAllTransaction();
        logger.info("All Transaction info " + listOfTransactions.toString());
        return listOfTransactions;
    }

    @PostMapping("/transactions")// Add a transaction into DB
    public ModelAndView addTransaction(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                       TransactionDto transactionDto) {
        ModelAndView modelAndView = new ModelAndView();
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
            user = userService.getUser(user.getId());
            modelAndView.addObject(user);
            modelAndView.setViewName("transaction_success");
        } else {
            modelAndView.setViewName("transaction_fail");
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
