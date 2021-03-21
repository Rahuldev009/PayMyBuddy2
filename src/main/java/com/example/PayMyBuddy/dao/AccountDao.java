package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.model.Account;


import java.util.List;

public interface AccountDao {

    public Account addAccount (Account account);

    public List<Account> getAllAccounts();

    public Account getAccount(int id);

    public void updateAccount(Account account);

    public Account deleteAccount(int id);

}
