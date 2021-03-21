package com.example.PayMyBuddy.controller;

import com.example.PayMyBuddy.model.Account;
import com.example.PayMyBuddy.model.Connection;
import com.example.PayMyBuddy.service.AccountService;
import com.example.PayMyBuddy.service.ConnectionService;
import com.example.PayMyBuddy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public class AccountController {
    private static final Logger logger = LogManager.getLogger(AccountController.class);

    private AccountService accountService;

    @Autowired
    public void AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable int id) {
        Account account = accountService.getAccount(id);
        logger.info("Account info by Id" + account.toString());
        return account;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public List<Account> getAllAccounts() {
        List<Account> listOfAccounts = accountService.getAllAccounts();
        logger.info("All Account info " + listOfAccounts.toString());
        return listOfAccounts;
    }

    @RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
    public Account deleteAccount(@PathVariable("id") int id) {
        Account account  = accountService.deleteAccount(id);
        logger.info("deleted Account info " + account.toString());
        return account;
    }

}
