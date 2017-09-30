package com.fortune.repository;

import com.fortune.model.Client;
import com.fortune.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{


    @Query("select t from Transaction t where t.account.client =:theclient")
    List<Transaction> findTransactionByClient(@Param("theclient") Client client);



}
