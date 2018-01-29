package com.fortune.controller;

import com.fortune.model.*;
import com.fortune.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    ExpenseService expenseService;

    @Autowired
    ClientService clientService;

    @Autowired
    CostsService costsService;

    @Autowired
    CaseService caseService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/addexpense/attendanceId", method = RequestMethod.GET)
    public String captureExpenseForm(Model model, @RequestParam("attendanceId")Long attendanceId){
        Attendance attendance = attendanceService.findById(attendanceId);
        Case aCase = attendance.getaCase();
        Client client = aCase.getClient();

        System.out.println("************* attendance id passed::::"+attendanceId);
        List<Costs> costsList = costsService.findAllCosts();
        model.addAttribute("attendanceId", attendanceId);
        model.addAttribute("clientId", client.getId());
        model.addAttribute("client", client);
        model.addAttribute("expense", new Expense());
        model.addAttribute("costsList", costsList);
        return "expense";
    }




    @RequestMapping(value = "/saveexpense", method = RequestMethod.POST)
    public String captureExepnse(@Valid Expense expense, BindingResult bindingResult,
                                 Model model, @RequestParam("clientId")Long clientId,
                                 @RequestParam("attendanceId")Long attendanceId){


        Client client = clientService.findClientById(clientId);
        Attendance attendance = attendanceService.findById(attendanceId);
        expense.setClient(client);
        expense.setLawyer(userService.loggedInuser());
        expense.setAttendance(attendance);
        expense.setRequisitionmade(Boolean.FALSE);
        expenseService.save(expense);
        model.addAttribute("successMessage", "saved!");
        return "redirect:/viewcase/caseId?caseId="+ attendance.getaCase().getId();
    }

}
