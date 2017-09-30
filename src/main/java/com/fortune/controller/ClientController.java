package com.fortune.controller;

import com.fortune.model.*;
import com.fortune.service.*;
import com.fortune.util.DateConveter;
import com.fortune.util.RateFormater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class ClientController {

    @Autowired
    CityService cityService;

    @Autowired
    ClientService clientService;


    @Autowired
    RoleService roleService;

    @Autowired
    CaseService caseService;

    @Autowired
    MatterService matterService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    RateService rateService;

    @Autowired
    RequisitionService requisitionService;


    
  @Autowired
  TransactionService transactionService;



    @RequestMapping(value = "/createclient", method = RequestMethod.GET)
    public String getUserAddForm(Model model){


        model.addAttribute("cityList" , cityService.findAllcities());
        model.addAttribute("client", new Client());
        return "clientregistration";
    }
    @RequestMapping(value = "/createclient", method = RequestMethod.POST)
    public String addClient(Model model, @Valid Client client, BindingResult bindingResult){
        clientService.saveClient(client);
        model.addAttribute("user", new User());
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("clientList" , clientService.findAll());
        model.addAttribute("sucessMessage", "client added");
        return "clientregistration";
    }



    @RequestMapping(value = "/viewclient/id", method = RequestMethod.GET)
    public String viewClient(Model model,@RequestParam("id") Long id){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User lawyer = userService.findUserByEmail(authentication.getName());


        Client client = clientService.findClientById(id);
        List<Case> clientCaseList = caseService.findAllCasesByClient(client);
if(clientCaseList != null) {
    clientCaseList.forEach(aCase -> {

        Rate lawyerRate = rateService.findByUser(lawyer);
        Double amount = lawyerRate.getAmount() / 60;
        aCase.setAmount(((15 / 100) * amount * aCase.getTimeSpent()) + amount * aCase.getTimeSpent());

    });
}
        model.addAttribute("client", client);

        model.addAttribute("clientid", id);
        model.addAttribute("clientCaseList", clientCaseList);
        return "clientview";
    }

    @RequestMapping(value = "/client/addaccount/id", method = RequestMethod.GET)
    public String addClientAccount(Model model,@RequestParam("id") Long clientId){

        Client client = clientService.findClientById(clientId);
        model.addAttribute("clientId", clientId);
        model.addAttribute("client", client);
        model.addAttribute("account", new Account());
        model.addAttribute("message", "adding account for "+client.getName()+" "+ client.getLastName());
        return "accounts";
    }

    @RequestMapping(value = "/client/addaccount", method = RequestMethod.POST)
    public String saveClientAccount(@Valid Account account, BindingResult bindingResult, Model model,@RequestParam("clientId") Long clientId){

        Client client = clientService.findClientById(clientId);
         account.setClient(client);
        accountService.save(account);
        model.addAttribute("successMessage", "account Added");
        return "accounts";
    }

    @RequestMapping(value = "/client/transaction/id", method = RequestMethod.GET)
    public String transactionForm(Model model,@RequestParam("id") Long accountId){

        Account account = accountService.findByID(accountId);
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("accountId", accountId);
        model.addAttribute("account", account);
        model.addAttribute("message", "adding account for "+account.getClient().getName()+" "+ account.getClient().getLastName());
        return "transaction";
    }


    @RequestMapping(value = "/viewpendingrequisitions", method = RequestMethod.GET)
    public String viewPendingRequisition(Model model){

        List<Requisition> pendingrequisitionList = requisitionService.findRequisitionByRequisitionStatus(RequisitionStatus.PENDING);
        model.addAttribute("list", pendingrequisitionList);
        return "requisitionlist";
        
    }

        
    @RequestMapping(value = "/client/transaction", method = RequestMethod.POST)
    public String makeTransaction(@Valid Transaction transaction, BindingResult bindingResult,Model model,@RequestParam("accountId") Long accountId){

        Account account = accountService.findByID(accountId);
        transaction.setAccount(account);
        transactionService.save(transaction);
        model.addAttribute("sussessMessage", "Transaction Successful !");
        return "transaction";
    }



    @RequestMapping(value = "/addcase", method = RequestMethod.GET)
    public String captureCaseForm(Model model, @RequestParam("id")Long clientid){

        Client client = clientService.findClientById(clientid);
        List<Matter> matterList = matterService.findAllMatters();
        model.addAttribute("clientId", client.getId());
        model.addAttribute("client", client);
        model.addAttribute("case", new Case());
        model.addAttribute("matterList", matterList);
        return "case";
    }

    @RequestMapping(value = "/addnewcase", method = RequestMethod.GET)
    public String captureTimeForm(Model model, @RequestParam("id")Long clientid){
        Client client = clientService.findClientById(clientid);
        List<Matter> matterList = matterService.findAllMatters();
        model.addAttribute("clientId", client.getId());
        model.addAttribute("client", client);
        model.addAttribute("case", new Case());
        model.addAttribute("matterList", matterList);
        return "recorder";
    }



    @RequestMapping(value = "/savecase", method = RequestMethod.POST)
    public String captureCase(@Valid Case aCase, BindingResult bindingResult, Model model, @RequestParam("clientId")Long clientId
                         ,@RequestParam("timeSpent") String timeSpent
    ){

        Client client = clientService.findClientById(clientId);
        Long duration = RateFormater.convertToMinutes(timeSpent);
        aCase.setClient(client);
        aCase.setTimeSpent(duration);
        caseService.save(aCase);
        model.addAttribute("successMessage", "saved!");
        return "redirect:/viewclient/id?id="+ aCase.getClient().getId();
    }

    @RequestMapping(value = "/charge", method = RequestMethod.GET)
    public void getCharge(Model model, @RequestParam("clientId") Long clientId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User lawyer = userService.findUserByEmail(authentication.getName());
        Client client = clientService.findClientById(clientId);

        Case aCase = caseService.findAllCasesByClient(client).get(0);

        Rate rate = rateService.findByUser(lawyer);

        Double amount = rate.getAmount();

        Double bill = amount * (aCase.getTimeSpent()/60);

        model.addAttribute("bill", bill);

        System.out.println(">>>>>>>>>>>>>>  amount is"+ bill);

    }




    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    public String viewRate(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User lawyer = userService.findUserByEmail(authentication.getName());

        model.addAttribute("user", lawyer);

        model.addAttribute("lawyerId", lawyer.getId());

        System.out.println(">>>>>>>>>>>>>>in get lawyer is "+ lawyer.getEmail());

        Rate rate = rateService.findByUser(lawyer);
        if(rate!=null){
            model.addAttribute("rate", rate);

        }

        else {
            model.addAttribute("rate", new Rate());

        }


        return "rate";

    }


    @RequestMapping(value = "/saverate", method = RequestMethod.POST)
    public String saveRate(@Valid Rate rate, BindingResult bindingResult, Model model,@RequestParam("lawyerId") String lawyerId
             ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User lawyer = userService.findUserByEmail(authentication.getName());
        Rate rate1 = rateService.findByUser(lawyer);
        if(rate1!=null) {
            rate1.setUser(lawyer);
            rateService.save(rate1);
        }



       rate.setUser(lawyer);
         rateService.save(rate);
        model.addAttribute("successMessage", "Rate Saved!");
        return "rate";
    }





}

