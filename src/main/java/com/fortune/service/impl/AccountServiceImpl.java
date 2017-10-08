package com.fortune.service.impl;


import com.fortune.model.Account;
import com.fortune.model.Client;
import com.fortune.repository.AccountRepository;
import com.fortune.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account findAccountByClient(Client client) {
        return accountRepository.findAccountByClient(client);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account findByID(Long accountID) {
        return accountRepository.findOne(accountID);
    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }
}
