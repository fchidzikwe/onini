package com.fortune.service.impl;

import com.fortune.model.Case;
import com.fortune.model.Client;
import com.fortune.model.Expense;
import com.fortune.model.User;
import com.fortune.repository.ExpenseRepository;
import com.fortune.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fchidzikwe on 10/4/17
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    ExpenseRepository expenseRepository;

    @Override
    public void save(Expense expense) {
        expenseRepository.save(expense);
    }

    @Override
    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    @Override
    public List<Expense> findAllByClient(Client client) {
        return expenseRepository.findAllByClient(client);
    }

    @Override
    public List<Expense> findAllByACase(Case aCase) {
        return expenseRepository.findAllByACase(aCase);
    }

    @Override
    public List<Expense> findExpenseByLawyer(User user) {
        return expenseRepository.findExpenseByLawyer(user);
    }

    @Override
    public List<Expense> findExpenseByPriceGreaterThan(Double price) {
        return expenseRepository.findExpenseByPriceGreaterThan(price);
    }

    @Override
    public List<Expense> findExpenseByPriceGreaterThanAndLawyer(Double price, User lawyer) {
        return expenseRepository.findExpenseByPriceGreaterThanAndLawyer(price, lawyer);
    }
}
