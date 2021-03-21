package com.example.PayMyBuddy.service;

import com.example.PayMyBuddy.controller.ConnectionController;
import com.example.PayMyBuddy.dao.TransactionDao;
import com.example.PayMyBuddy.dto.TransactionDto;
import com.example.PayMyBuddy.model.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    private static final Logger logger = LogManager.getLogger(TransactionService.class);

    @Autowired
    TransactionDao transactionDao;

    @Transactional
    public List<Transaction> getAllTransaction() {
        return transactionDao.getAllTransaction();
    }

    @Transactional
    public Transaction getTransaction(int id) {
        return transactionDao.getTransaction(id);
    }

    @Transactional
    public Transaction addTransaction(Transaction transaction) {
        return transactionDao.addTransaction(transaction);
    }

    @Transactional
    public void updateTransaction(Transaction transaction) {
        transactionDao.updateTransaction(transaction);
    }

    @Transactional
    public Transaction deleteTransaction(int id) {
        return transactionDao.deleteTransaction(id);
    }

    public List<TransactionDto> getTransactionList(List<Transaction> allTransaction, int id) {
        List<TransactionDto> transactionsDtoList = new ArrayList<>();
        Transaction transaction;
        for (int i = allTransaction.size() - 1; i >= 0; i--) {
            if (allTransaction.get(i).getUser().getId() == id) {
                transaction = allTransaction.get(i);
                TransactionDto transactionDto = new TransactionDto();
                transactionDto.setName(transaction.getReceiver().getUserName());
                transactionDto.setAmount(transaction.getAmount());
                transactionDto.setDescription(transaction.getDescription());
                transactionDto.setCurrency(transaction.getUser().getCurrency());
                transactionsDtoList.add(transactionDto);
            }
        }
        logger.info("transaction list"+transactionsDtoList);
        return transactionsDtoList;
    }

}
