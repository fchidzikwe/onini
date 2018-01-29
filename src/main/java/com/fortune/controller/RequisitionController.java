package com.fortune.controller;


import com.fortune.model.*;
import com.fortune.service.*;
import com.fortune.util.DateConveter;
import com.fortune.util.SystemUtils;
import org.apache.regexp.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;
import java.util.*;

@Controller
 class RequisitionController {
    @Autowired
    RequisitionService requisitionService;

    @Autowired
    SystemUtils systemUtils;

    @Autowired
    CaseService caseService;


    @Autowired
    ExpenseService expenseService;

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;


    @RequestMapping(value = "/getgrequistionform/caseId" ,method = RequestMethod.GET)
    public String getRequisitionForm(Model model, @RequestParam("caseId") Long caseId, @RequestParam("expenseId") Long expenseId){
        Case clientCase = caseService.findCaseById(caseId);
        Client client =  clientCase.getClient();
        model.addAttribute("client", client);
        model.addAttribute("requisition", new Requisition());
        model.addAttribute("caseId",caseId );
        model.addAttribute("expenseId",expenseId );
        return "requisition";
    }



    @RequestMapping(value = "/readrequsition/id" ,method = RequestMethod.GET)
    public String readInbox(Model model, @RequestParam("id") long requisitionId, RedirectAttributes redirectAttributes){
        Requisition requisition = requisitionService.findByID(requisitionId);
        requisition.setView(1);
        requisitionService.saveRequisitionWithoutChangingView(requisition);
        List<Requisition> notpending = requisitionService.findRequisitionByStatusIsNotAndMadeby(RequisitionStatus.PENDING, 0);
        Boolean notpend =true;
        redirectAttributes.addFlashAttribute("notpend", notpend);
        redirectAttributes.addFlashAttribute("list", notpending);
        return "redirect:/viewinbox";
    }




    @RequestMapping(value = "/saverequisition/{expenseId}" ,method = RequestMethod.POST)
    public String makeRequisition(Model model, @ModelAttribute Requisition requisition,
                                  @PathVariable("expenseId") Long expenseId

    ){
        Expense expense = expenseService.findOne(expenseId);
        createRequisitionModel(model,requisition, expense);
        requisitionService.save(requisition);
        return "redirect:/viewclient/id?id="+ expense.getAttendance().getaCase().getClient().getId();
    }

    private void createRequisitionModel(Model model,Requisition requisition, Expense expense) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByEmail(auth.getName());


        Case aCase = expense.getAttendance().getaCase();
        expense.setRequisitionmade(Boolean.TRUE);
        requisition.setRequisitionDate(new Date());
        requisition.setaCase(expense.getAttendance().getaCase());
        requisition.setMadeby(loggedInUser);
        requisition.setExpense(expense);
        requisition.setStatus(RequisitionStatus.PENDING);
        requisition.setAmount(expense.getPrice()* expense.getQuantity());
        requisition.setPurpose(expense.getDescription());
        requisition.setRequisitionNumber(systemUtils.generateSystemNumbers("RQ",getLastRequisitionNumber()));
        requisition.setPayee(expense.getClient().getName() + " "+expense.getClient().getLastName());
        model.addAttribute("requisition", new Requisition());
        model.addAttribute("caseId",aCase.getId() );
    }



    @RequestMapping(value = "/viewpendingrequisitions", method = RequestMethod.GET)
    public String viewPendingRequisition(Model model){
        Boolean pending = true;
        List<Requisition> pendingrequisitionList = requisitionService.findRequisitionByRequisitionStatus(RequisitionStatus.PENDING);
        model.addAttribute("list", pendingrequisitionList);
        model.addAttribute("pending", pending);
        return "requisitionlist";
    }

    @RequestMapping(value = "/viewacceptedrequisitions", method = RequestMethod.GET)
    public String viewAcceptedRequisition(Model model){
        Boolean accepted = true;
        List<Requisition> acceptedrequisitionList = requisitionService.findRequisitionByRequisitionStatus(RequisitionStatus.ACCEPTED);
        model.addAttribute("list", acceptedrequisitionList);
        model.addAttribute("accepted", accepted);
        return "requisitionlist";
    }

    @RequestMapping(value = "/viewdeclinedrequisitions", method = RequestMethod.GET)
    public String viewDeclinedRequisition(Model model){
        Boolean declined = true;
        List<Requisition> declinedrequisitionList = requisitionService.findRequisitionByRequisitionStatus(RequisitionStatus.DECLINED);
        model.addAttribute("list", declinedrequisitionList);
        model.addAttribute("declined", declined);
        return "requisitionlist";
    }

    @RequestMapping(value = "/acceptrequsition/id", method = RequestMethod.GET)
    public String acceptAppointment(Model model, @RequestParam(value = "id", required = true) Long id) {
        Requisition requisition = requisitionService.findByID(id);
        requisition.setStatus(RequisitionStatus.ACCEPTED);
       Case aCase = requisition.getaCase();
        requisitionService.save(requisition);
       Transaction transaction = transactionService.findTransactionByCase(aCase);

       if(transaction!=null){
           transaction.setAmount(transaction.getAmount() -requisition.getAmount() );
           transactionService.save(transaction);
       }

        aCase.setAmount(aCase.getAmount() + requisition.getAmount());
        caseService.save(aCase);
        Boolean accepted = true;
        model.addAttribute("accepted",accepted);
        return "redirect:/home";
    }

    @RequestMapping(value = "/declinerequsition/id", method = RequestMethod.GET)
    public String declineRequisition(Model model, @RequestParam(value = "id", required = true) Long id) {
        Requisition requisition = requisitionService.findByID(id);
        requisition.setStatus(RequisitionStatus.DECLINED);
        requisitionService.save(requisition);
        return "redirect:/home";
    }

    @RequestMapping(value = "/managerequsition/id", method = RequestMethod.GET)
    public String managePendingRequisition(Model model, @RequestParam(value = "id", required = true) Long id) {
        Requisition requisition = requisitionService.findByID(id);
        Client client = requisition.getaCase().getClient();

        Case  aCase = requisition.getaCase();

        Transaction transaction = transactionService.findTransactionByCase(aCase);

        Boolean pending = true;
         Double balance =0.0;
        if(transaction!=null){
            balance = transaction.getAmount();
        }
       Expense expense = requisition.getExpense();
        List<Costs> costsList = expense.getCosts();
            costsList.forEach(costs -> {
                expense.setCostName(costs.getName());
            });
        Double totalExpenseAmount =0.0;
        Double expenseAmount = expense.getQuantity() * expense.getPrice();
        totalExpenseAmount= totalExpenseAmount+ expenseAmount;
        model.addAttribute("totalExpenseAmount", totalExpenseAmount);
        model.addAttribute("expense", expense);
        model.addAttribute("pending", pending);
       model.addAttribute("balance", balance);
        model.addAttribute("list", requisition);
        return "managerequisitionlist";

    }

    public Long getLastRequisitionNumber() {

        List<Requisition> requisitionList = requisitionService.findAllRequisition();
        if(requisitionList==null){
            return 1L;
        }
        else {
            if(requisitionList.size()==0){
                return 1L;
            }else {
                return requisitionList.get(requisitionList.size() - 1).getId();
            }
        }
    }
}
