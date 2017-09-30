package com.fortune.repository;

import com.fortune.model.Account;
import com.fortune.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface AccountRepository extends JpaRepository<Account, Long> {

     Account findAccountByClient(Client client);

    @Override
    List<Account> findAll();


}
