package com.fortune.controller;

import com.fortune.model.Case;
import com.fortune.model.Client;
import com.fortune.model.Costs;
import com.fortune.model.Expense;
import com.fortune.service.CaseService;
import com.fortune.service.ClientService;
import com.fortune.service.CostsService;
import com.fortune.service.ExpenseService;
import com.fortune.util.RateFormater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * @author fchidzikwe on 10/4/17
 */
@Controller
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @Autowired
    ClientService clientService;

    @Autowired
    CostsService costsService;

    @Autowired
    CaseService caseService;



    @RequestMapping(value = "/addexpense/caseId", method = RequestMethod.GET)
    public String captureExpenseForm(Model model, @RequestParam("caseId")Long caseId){
        Case aCase = caseService.findCaseById(caseId);
        Client client = aCase.getClient();
        List<Costs> costsList = costsService.findAllCosts();
        model.addAttribute("caseId", aCase.getId());
        model.addAttribute("clientId", client.getId());
        model.addAttribute("client", client);
        model.addAttribute("expense", new Expense());
        model.addAttribute("costsList", costsList);
        return "expense";
    }




    @RequestMapping(value = "/saveexpense", method = RequestMethod.POST)
    public String captureExepnse(@Valid Expense expense, BindingResult bindingResult,
                                 Model model, @RequestParam("clientId")Long clientId,
                                 @RequestParam("caseId")Long caseId){

        Client client = clientService.findClientById(clientId);
        Case aCase = caseService.findCaseById(caseId);
        expense.setClient(client);
        expense.setaCase(aCase);
        expenseService.save(expense);
        model.addAttribute("successMessage", "saved!");
        return "redirect:/viewclient/id?id="+ expense.getClient().getId();
    }

}
