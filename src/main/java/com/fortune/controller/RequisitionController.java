package com.fortune.controller;


import com.fortune.model.Case;
import com.fortune.model.Requisition;
import com.fortune.model.RequisitionStatus;
import com.fortune.model.User;
import com.fortune.service.CaseService;
import com.fortune.service.RequisitionService;
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
import java.util.Date;

@Controller
public class RequisitionController {
    @Autowired
    RequisitionService requisitionService;

    @Autowired
    CaseService caseService;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/getgrequistionform/caseId" ,method = RequestMethod.GET)
    public String getRequisitionForm(Model model, @RequestParam("caseId") Long caseId){


        model.addAttribute("requisition", new Requisition());
        model.addAttribute("caseId",caseId );

        return "requisition";
    }




    @RequestMapping(value = "/saverequisition" ,method = RequestMethod.POST)
    public String makeRequisition(@Valid Requisition requisition, BindingResult bindingResult, Model model, @RequestParam("caseId") Long caseId ,
                                  @RequestParam("requisitionDate") String requisitionDate
    ){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser = userService.findUserByEmail(auth.getName());

        Case aCase = caseService.findCaseById(caseId);

        Date dateOfRequisition = DateConveter.stringToDate(requisitionDate);
        requisition.setaCase(aCase);
        requisition.setRequisitionDate(dateOfRequisition);
        requisition.setMadeby(loggedInUser);
        requisition.setStatus(RequisitionStatus.PENDING);
        requisitionService.save(requisition);
        model.addAttribute("requisition", new Requisition());
        model.addAttribute("caseId",caseId );

        return "requisition";
    }

}
