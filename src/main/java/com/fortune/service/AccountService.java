package com.fortune.service;

import com.fortune.model.Account;
import com.fortune.model.Client;

import java.util.List;

public interface AccountService {

    List<Account> findAllAccounts();

    Account findAccountByClient(Client client);

    void save(Account account);

    Account findByID(Long accountID);

    void delete(Account account);
}
