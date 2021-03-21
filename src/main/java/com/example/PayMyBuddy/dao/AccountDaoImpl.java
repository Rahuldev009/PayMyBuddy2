package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.model.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Account addAccount(Account account) {
        Session session = sessionFactory.openSession();
        session.save(account);
        session.close();
        return account;
    }

    @Override
    public List<Account> getAllAccounts() {
        Session session = sessionFactory.openSession();
        List<Account> account = (List<Account>) session.createQuery("from Account").list();
        session.close();
        return account;
    }

    @Override
    public Account getAccount(int id) {
        Session session = sessionFactory.openSession();
       Account account = session.get(Account.class, id);
        session.close();
        return account;
    }

    @Override
    public void updateAccount(Account account) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(account);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Account deleteAccount(int id) {
        Session session = sessionFactory.openSession();
        Account account = (Account) session.get(Account.class, id);
        session.delete(account);
        session.close();
        return account;
    }

}
