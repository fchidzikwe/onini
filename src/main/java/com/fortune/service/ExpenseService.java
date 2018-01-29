package com.fortune.service;

import com.fortune.model.Case;
import com.fortune.model.Client;
import com.fortune.model.Expense;
import com.fortune.model.User;

import java.util.List;

/**
 * @author fchidzikwe on 10/4/17
 */
public interface ExpenseService {

    Expense findOne(Long expenseId);

    void save(Expense expense);

    List<Expense> findAll();

    List<Expense> findAllByClient(Client client);

    List<Expense> findAllByClientAndRequisitionmade(Client client, Boolean aBoolean);

  // List<Expense> findAllByACaseAndRequisitionMade(Case aCase, Boolean aBoolean);

    List<Expense> findExpenseByLawyer(User user);

    List<Expense> findExpenseByPriceGreaterThan(Double price);

    List<Expense> findExpenseByPriceGreaterThanAndLawyer(Double price,User lawyer);

    List<Expense> findAllExpensesWithoutReqisition(Boolean aBoolean);

    List<Expense> findAllExepnseWithoutReqisitionAndClient(Boolean  aBoolean, Client client);
}
