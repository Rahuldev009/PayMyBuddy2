package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.model.Transaction;

import java.util.List;

public interface TransactionDao {

    public List<Transaction> getAllTransaction();

    public Transaction getTransaction(int id);

    public Transaction addTransaction(Transaction transaction);

    public Transaction updateTransaction(Transaction transaction);

    public Transaction deleteTransaction(int id);

}
