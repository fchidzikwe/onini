package com.fortune.service;

import com.fortune.model.Client;
import com.fortune.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findTransactionByClient(Client client);

    List<Transaction> findAllTransactions();

    void save(Transaction transaction);

    Transaction findTransactionById(Long id);

    void delete(Transaction transaction);



}
