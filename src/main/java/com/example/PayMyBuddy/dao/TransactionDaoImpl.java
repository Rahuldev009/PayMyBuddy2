package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.model.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionDaoImpl implements TransactionDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Transaction> getAllTransaction() {
        Session session = sessionFactory.openSession();
        List<Transaction> transaction = session.createQuery("from Transaction").list();
        session.close();
        return transaction;
    }

    @Override
    public Transaction getTransaction(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.get(Transaction.class, id);
        session.close();
        return transaction;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        Session session = sessionFactory.openSession();
        session.save(transaction);
        session.close();
        return transaction;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(transaction);
        session.getTransaction().commit();
        session.close();
        return transaction;
    }

    @Override
    public Transaction deleteTransaction(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = (Transaction) session.get(Transaction.class, id);
        session.delete(transaction);
        session.close();
        return transaction;
    }

}
