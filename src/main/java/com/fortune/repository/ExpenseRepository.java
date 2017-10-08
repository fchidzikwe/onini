package com.fortune.repository;

import com.fortune.model.Case;
import com.fortune.model.Client;
import com.fortune.model.Expense;
import com.fortune.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fchidzikwe on 10/4/17
 */
public interface ExpenseRepository extends JpaRepository<Expense, Long>{

    List<Expense> findAllByClient(Client client);

    List<Expense> findAllByACase(Case aCase);

    List<Expense> findExpenseByLawyer(User user);

    List<Expense> findExpenseByPriceGreaterThan(Double price);

    List<Expense> findExpenseByPriceGreaterThanAndLawyer(Double price,User lawyer);
}
