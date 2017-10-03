package com.fortune.controller;


import com.fortune.model.*;
import com.fortune.service.CaseService;
import com.fortune.service.RequisitionService;
import com.fortune.service.TransactionService;
import com.fortune.service.UserService;
import com.fortune.util.DateConveter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.*;

@Controller
public class RequisitionController {
    @Autowired
    RequisitionService requisitionService;

    @Autowired
    CaseService caseService;



    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;


    @RequestMapping(value = "/getgrequistionform/caseId" ,method = RequestMethod.GET)
    public String getRequisitionForm(Model model, @RequestParam("caseId") Long caseId){
        Case clientCase = caseService.findCaseById(caseId);

        Client client =  clientCase.getClient();
        model.addAttribute("client", client);
        model.addAttribute("requisition", new Requisition());
        model.addAttribute("caseId",caseId );

        return "requisition";
    }



    @RequestMapping(value = "/viewinbox" ,method = RequestMethod.GET)
    public String viewInbox(Model model){
        List<Requisition> notpending = requisitionService.findRequisitionByStatusIsNotAndMadeby(RequisitionStatus.PENDING);

        Boolean notpend =true;
        model.addAttribute("notpend", notpend);

        model.addAttribute("list", notpending);


        return "requisitionlist";
    }



    @RequestMapping(value = "/saverequisition" ,method = RequestMethod.POST)
    public String makeRequisition(@Valid Requisition requisition, BindingResult bindingResult, Model model, @RequestParam("caseId") Long caseId ,
                                  @RequestParam("requisitionDate") String requisitionDate
    ){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByEmail(auth.getName());

        Case aCase = caseService.findCaseById(caseId);
        aCase.setRequisitionmade(Boolean.TRUE);

        Date dateOfRequisition = DateConveter.stringToDate(requisitionDate);

        requisition.setaCase(aCase);
        requisition.setRequisitionDate(dateOfRequisition);
        requisition.setMadeby(loggedInUser);
        requisition.setStatus(RequisitionStatus.PENDING);
        requisitionService.save(requisition);
        model.addAttribute("requisition", new Requisition());
        model.addAttribute("caseId",caseId );


        return "redirect:/viewclient/id?id="+ aCase.getClient().getId();
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
        List<Transaction> transactionList = transactionService.findTransactionByClient(requisition.getaCase().getClient());


        Double balance =0.0;
        Double requestedAmount =requisition.getAmount();
        int t=0;
        for(Transaction transaction: transactionList){
            t= t+1;

          balance =   transaction.getAmount() ;
            if(balance < requestedAmount && t< transactionList.size() ){
                requestedAmount =    (requestedAmount-balance);
                transaction.setAmount(0.0);
                transactionService.save(transaction);
            }else{
                balance =  balance - requestedAmount;
                transaction.setAmount(balance);
                transactionService.save(transaction);
                break;
            }

//            if(t==transactionList.size() && balance < requestedAmount ){
//                balance =  balance - requestedAmount;
//                transaction.setAmount(balance);
//                transactionService.save(transaction);
//            }

        }

        requisitionService.save(requisition);
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
        List<Transaction> transactionList = transactionService.findTransactionByClient(client);
        Boolean pending = true;
       Double balance =0.0;
       for(Transaction transaction: transactionList){
           balance = balance + transaction.getAmount();
       }
        model.addAttribute("pending", pending);
       model.addAttribute("balance", balance);
        model.addAttribute("list", requisition);
        return "managerequisitionlist";

    }




}
