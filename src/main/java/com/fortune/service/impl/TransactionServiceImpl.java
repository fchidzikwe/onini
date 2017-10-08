package com.fortune.service.impl;

import com.fortune.model.Client;
import com.fortune.model.Transaction;
import com.fortune.repository.TransactionRepository;
import com.fortune.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findTransactionByClient(Client client) {
        return transactionRepository.findTransactionByClient(client);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public void save(Transaction transaction) {
            transactionRepository.save(transaction);
    }

    @Override
    public Transaction findTransactionById(Long id) {
        return transactionRepository.findOne(id);
    }

    @Override
    public void delete(Transaction transaction) {
        transactionRepository.delete(transaction);
    }
}
