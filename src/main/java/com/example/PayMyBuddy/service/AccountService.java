package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.dao.AccountDao;
import com.example.PayMyBuddy.dto.UserDto;
import com.example.PayMyBuddy.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountDao accountDao;

    @Transactional
    public Account addAccount(Account account) {
        return accountDao.addAccount(account);
    }

    @Transactional
    public Account getAccount(int id) {
        return accountDao.getAccount(id);
    }

    @Transactional
    public List<Account> getAllAccounts(){
        return accountDao.getAllAccounts();
    }

    @Transactional
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Transactional
    public Account deleteAccount(int id) {
        return accountDao.deleteAccount(id);
    }


    public Account mapUserDtotoAccount(UserDto userDto) {
        Account account = new Account();
        account.setAccountNo(userDto.getAccountNo());
        account.setBank(userDto.getBankName());
        return account;
    }
}
