package com.fortune.controller;

import com.fortune.model.*;
import com.fortune.service.*;
import com.fortune.util.DateConveter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by fortune on 7/11/17.
 */

@Controller
public class LoginController {

    @Autowired
    RoleService  roleService;

    @Autowired
    UserService userService;

    @Autowired
    CountryService countryService;

    @Autowired
    ClientService clientService;

    @Autowired
    RequisitionService requisitionService;

    @Autowired
    AccountService accountService;

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(){
        return "indexlogin";
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWelcome(){
        return "index";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration (Model model) {
        List<Role> roleList = roleService.findAll();
        Role adminRole = roleService.findByRole("ADMIN");
        //roleList.remove(adminRole);
        model.addAttribute("genderList", Gender.values());
        model.addAttribute("user", new User());
        model.addAttribute("roleList", roleList);
        return   "registration";
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage (Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User lawyer = userService.findUserByEmail(authentication.getName());

        // for accountant
        List<Requisition> allRequisutions = requisitionService.findAllRequisition();
        List<Requisition> allRequisitionsMadeByLawyer = requisitionService.findRequisitionMadeByLawyer(lawyer);
        List<Requisition> pendingRequisition = requisitionService.findRequisitionByRequisitionStatus(RequisitionStatus.PENDING);
        List<Requisition> acceptedRequisition = requisitionService.findRequisitionByRequisitionStatus(RequisitionStatus.ACCEPTED);
        List<Requisition> declinedRequisition = requisitionService.findRequisitionByRequisitionStatus(RequisitionStatus.DECLINED);

        List<Message> unreadmessages = messageService.findByMessageToAndRead(lawyer,0);
        //for lawyer

        List<Requisition> inboxReq = requisitionService.findRequisitionByStatusIsNotAndMadeby(RequisitionStatus.PENDING,0);
        int allinboxSize = inboxReq.size()+ unreadmessages.size();
       model.addAttribute("inboxReqSize", "MESSAGES(" +allinboxSize+ ")");
        List<Role> roleList = roleService.findAll();
        Role adminRole = roleService.findByRole("ADMIN");
        roleList.remove(adminRole);
        model.addAttribute("account", new Account());
        model.addAttribute("allRequisutions",allRequisutions);
        model.addAttribute("pendingRequisitionSize", "Pending Requisitions (" + pendingRequisition.size() + ")");
        model.addAttribute("acceptedRequisitionSize", "Accepted Requisitions (" + acceptedRequisition.size() + ")");
        model.addAttribute("declinedRequisitionSize", "Declined Requisitions (" + declinedRequisition.size() + ")");
        model.addAttribute("clientList" , clientService.findByUser(lawyer));
        model.addAttribute("user", new User());
        model.addAttribute("accountList", accountService.findAllAccounts());
        model.addAttribute("roleList", roleList);
        model.addAttribute("userList", userService.findAll());
        return   "home";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid User user,
                               RedirectAttributes redirectAttributes,
                               BindingResult bindingResult, Model  model, @RequestParam("dateRegistered") String dateRegistered){

       if(bindingResult.hasErrors()){
           List<Role> roleList = roleService.findAll();
           Role adminRole = roleService.findByRole("ADMIN");
           roleList.remove(adminRole);
           model.addAttribute("user", user);
           model.addAttribute("roleList", roleList);
           return "registration";
       }
        Date date = DateConveter.stringToDate(dateRegistered);
        user.setDateRegistered(date);
        user.setActive(1);
        userService.saveUser(user);
        redirectAttributes.addFlashAttribute("successMessage", "User Registred");
        redirectAttributes.addFlashAttribute("css", "alert alert-success");
        return "redirect:/login";
    }

    @ModelAttribute("user")
    public User user(){
        return new User();
    }

//    @ModelAttribute("user")
//    public User currentUser(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(authentication.getName());
//        if(user==null) {
//            return new User();
//        }else {
//            return user;
//        }
//    }
}
