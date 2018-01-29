package com.fortune.controller;

import com.fortune.model.*;
import com.fortune.service.*;
import com.fortune.util.DateConveter;
import com.fortune.util.RateFormater;
import com.fortune.util.SystemUtils;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
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
    SystemUtils systemUtils;

    @Autowired
    ExpenseService expenseService;

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

    @Autowired
    AttendanceService attendanceService;




    @RequestMapping(value = {"/createclient","/createclient/{clientType}" }, method = RequestMethod.GET)
    public String getUserAddForm(Model model, @PathVariable(value ="clientType", required = false) String clientType){
       int company  =1;
        Client client= new Client();
        if(clientType!=null){
            model.addAttribute("clientType",ClientType.COMPANY );
            client.setClientType(ClientType.COMPANY);
        }else{
            model.addAttribute("clientType",ClientType.INDIVIDUAL );
            client.setClientType(ClientType.INDIVIDUAL);
            company= 0;
            model.addAttribute("genderList", Gender.values());
        }
        model.addAttribute("cityList" , cityService.findAllcities());
        model.addAttribute("client", client);
        model.addAttribute("company",company);
        return "clientregistration";
    }

    @RequestMapping(value = "/createclient", method = RequestMethod.POST)
    public String addClient(Model model, @Valid Client client, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("cityList" , cityService.findAllcities());
            model.addAttribute("client", client);
            model.addAttribute("successMessage", bindingResult.getAllErrors());
            return "clientregistration";
        }else{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User lawyer = userService.findUserByEmail(authentication.getName());
            client.setUser(lawyer);
            clientService.saveClient(client);
            model.addAttribute("user", new User());
            model.addAttribute("roleList", roleService.findAll());
            model.addAttribute("userList", userService.findAll());
            model.addAttribute("clientList" , clientService.findByUser(lawyer));
            model.addAttribute("sucessMessage", "client added");
            return "redirect:/home";
        }
    }


    @RequestMapping(value = "/viewclient/id", method = RequestMethod.GET)
    public String viewClient(Model model,@RequestParam("id") Long id){
        Client client = clientService.findClientById(id);
        List<Case> clientCaseList = caseService.findAllCasesByClient(client);
        for(Case aCase: clientCaseList){
            List<Attendance> attendanceList = attendanceService.findAllByACase(aCase);
            Double amount = aCase.getAmount();
            Long timeSpent =0L;
            for(Attendance attendance: attendanceList){
                amount = amount + attendance.getAmount();
                timeSpent= timeSpent+ attendance.getTimeSpent();
            }

            List<Requisition> requisitionList = requisitionService.findRequisitionByACase(aCase);
            for(Requisition requisition: requisitionList){
                amount = amount + requisition.getAmount();
            }

            aCase.setAmount(amount);
            aCase.setTimeSpent(timeSpent);
        }
        model.addAttribute("client", client);
        model.addAttribute("clientid", id);
        model.addAttribute("clientCaseList", clientCaseList);
        return "clientview";
    }

    @RequestMapping(value = "/viewattendance/attendanceId", method = RequestMethod.GET)
    public String viewClientAttendance(Model model,@RequestParam("attendanceId") Long attendanceId){
        Attendance attendance = attendanceService.findById(attendanceId);
        List<Requisition> expenseList = requisitionService.findRequisitionByACase(attendance.getaCase());

        List<Requisition> newRequisitionList = new ArrayList<>();
        for(Requisition requisition:expenseList){
            if(requisition.getStatus().equals(RequisitionStatus.ACCEPTED)){
                newRequisitionList.add(requisition);
            }

        }
        Client client = attendance.getaCase().getClient();
        model.addAttribute("client", client);
        model.addAttribute("clientid", client.getId());
        model.addAttribute("expenseList",newRequisitionList);
        model.addAttribute("attendance", attendance);
        return "clientattendanceview";
    }


    @RequestMapping(value = "/client/addaccount/id", method = RequestMethod.GET)
    public String addClientAccount(Model model,@RequestParam("id") Long clientId){
        Client client = clientService.findClientById(clientId);
        model.addAttribute("clientId", clientId);
        model.addAttribute("client", client);
        model.addAttribute("account", new Account());
        model.addAttribute("message", "ADDING ACCOUNT FOR "+(client.getName()+" "+ client.getLastName()).toUpperCase());
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
        List<Case>  caseList = caseService.findAllCasesByClient(account.getClient());
        model.addAttribute("caseList", caseList);
        model.addAttribute("message", "TRANSACTION FOR "+(account.getClient().getName()+" "+ account.getClient().getLastName()).toUpperCase());
        return "transaction";
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

    @RequestMapping(value = "/getlawyers/clientId", method = RequestMethod.GET)
    public String getLawyers(Model model, @RequestParam("clientId")Long clientId){
       Role role = roleService.findByRole("LAWYER");
       Client client = clientService.findClientById(clientId);
       List<User> lawyers = userService.findUsersByRole(role);
       model.addAttribute("lawyerList", lawyers);
        model.addAttribute("client", client);
        return "lawyerList";
    }



    @RequestMapping(value = "/{lawyerId}/shareclient/id", method = RequestMethod.GET)
    public String getLawyer(RedirectAttributes redirectAttributes,Model model, @RequestParam("id")Long clientId, @PathVariable(value = "lawyerId") Long lawyerId){
        Client client = clientService.findClientById(clientId);
        User user = userService.findUserById(lawyerId);
        client.setUser(user);
        clientService.saveClient(client);
        redirectAttributes.addAttribute("successMessage", "Client Shared !");
        return "redirect:/home";
    }





    @RequestMapping(value = "/viewcase/caseId", method = RequestMethod.GET)
    public String viewClientCase(Model model, @RequestParam("caseId")Long caseID){
        Case aCase = caseService.findCaseById(caseID);
        List<Expense> expenseList = expenseService.findAllByClientAndRequisitionmade(aCase.getClient(),Boolean.FALSE);

       List<Attendance> attendanceList = attendanceService.findAllByACase(aCase);
        Double amount =0.0;
        attendanceList.forEach(attendance ->  {

            List<Requisition> expenses = attendanceService.getAllAttendanceExpenses(attendance);


            Double tE = 0.0;
            for(Requisition  expense: expenses){

                if(expense.getStatus().equals(RequisitionStatus.ACCEPTED)){
                    tE = tE + expense.getAmount();
                }

            }

            attendance.setTotalExpenseAmount(tE);

            Double amountInHours = attendance.getRate();
            long durationInMinutes= attendance.getTimeSpent();
            Double amountCharged = ((double)durationInMinutes /(double) 60) * amountInHours;
            Double VAT = 0.15 * amountCharged;
            attendance.setVat(VAT);
        });
        if(aCase!=null){
            amount =aCase.getAmount();
        }
        Long timeSpent =0L;
        for( Attendance attendance: attendanceService.findAllByACase(aCase)){
            amount = amount+ attendance.getAmount();
            timeSpent = timeSpent+ attendance.getTimeSpent();
        }

        List<Requisition> requisitionList= requisitionService.findRequisitionByACase(aCase);
        Double expenseAmount =0.0;

        for(Requisition requisition: requisitionList){
           if(requisition.getStatus().equals(RequisitionStatus.ACCEPTED)){
               expenseAmount=expenseAmount + (requisition.getAmount());
           }
        }

        timeSpent = timeSpent/60;
        aCase.setTimeSpent(timeSpent);
        aCase.setAmount(amount);
        model.addAttribute("case", aCase);
        model.addAttribute("expenseAmount", expenseAmount);
        model.addAttribute("client", aCase.getClient());
        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("caseId", aCase.getId());
       model.addAttribute("expenseList", expenseList);
        return "clientcaseview";
    }




    @RequestMapping(value = "/addnewattendance", method = RequestMethod.GET)
    public String captureTimeFormNewAttendance(Model model, @RequestParam("id")Long caseId){
        Case aCase = caseService.findCaseById(caseId);
        model.addAttribute("clientId", aCase.getClient().getId());
        model.addAttribute("client", aCase.getClient());
        model.addAttribute("aCase", aCase);
        model.addAttribute("attendance", new Attendance());
        return "recorder";
    }
    @RequestMapping(value = "/addattendance", method = RequestMethod.GET)
    public String captureCaseFormAttendance(Model model, @RequestParam("id")Long caseId){
        Case aCase = caseService.findCaseById(caseId);

        model.addAttribute("clientId", aCase.getClient().getId());
        model.addAttribute("client", aCase.getClient());
        model.addAttribute("aCase", aCase);
        model.addAttribute("attendance", new Attendance());
        return "attendance";
    }

    @RequestMapping(value = "/saveattendance", method = RequestMethod.POST)
    public String captureAttendance(@Valid Attendance attendance, BindingResult bindingResult, Model model
            ,@RequestParam("timeSpent") String timeSpent, @RequestParam("caseId") Long caseId){

        Case aCase = caseService.findCaseById(caseId);
        Client client = aCase.getClient();
        Long durationInMinutes = RateFormater.convertToMinutes(timeSpent);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User lawyer = userService.findUserByEmail(authentication.getName());
        Double amountInHours = 0.0;
        Rate lawyerRate = rateService.findByUser(lawyer);
        if(lawyerRate==null){
            return populateLawyerCaseModel(model, client);
        }
        else{
            amountInHours = lawyerRate.getAmount();
            Double amountCharged = ((double)durationInMinutes /(double) 60) * amountInHours;
            Double VAT = 0.15 * amountCharged;
            Double amountChargedVat = amountCharged + VAT;
            attendance.setAmount(amountChargedVat);
            attendance.setRate(lawyerRate.getAmount());
            attendance.setTimeSpent(durationInMinutes);
            attendance.setaCase(aCase);
            attendanceService.save(attendance);
            model.addAttribute("successMessage", "saved!");
            return "redirect:/viewclient/id?id=" + aCase.getClient().getId();
        }
    }

    @RequestMapping(value = "/savecase", method = RequestMethod.POST)
    public String captureCase(@Valid Case aCase, BindingResult bindingResult, RedirectAttributes model, @RequestParam("clientId")Long clientId,
                              @RequestParam("matterId")Long matterId
                         ){
        Client client = clientService.findClientById(clientId);

        Matter matter = matterService.findMatter(matterId);

        aCase.setCaseNumber(systemUtils.generateSystemNumbers("", getLastCaseNumber()));

            aCase.setMatter(matter);
            aCase.setClient(client);
            caseService.save(aCase);
            model.addFlashAttribute("successMessage", " case saved suceesfully!");
            return "redirect:/viewclient/id?id=" + aCase.getClient().getId();

    }

    private String populateLawyerCaseModel(Model model, Client client) {
        List<Matter> matterList = matterService.findAllMatters();
        model.addAttribute("clientId", client.getId());
        model.addAttribute("client", client);
        model.addAttribute("case", new Case());
        model.addAttribute("matterList", matterList);
        model.addAttribute("successMessage", "Please Set Your Rate First!");
        return "case";
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
    }


    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    public String viewRate(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User lawyer = userService.findUserByEmail(authentication.getName());
        model.addAttribute("user", lawyer);
        model.addAttribute("lawyerId", lawyer.getId());
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
    public String saveRate(@Valid Rate rate, BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User lawyer = userService.findUserByEmail(authentication.getName());
        Rate rate1 = rateService.findByUser(lawyer);
        if(bindingResult.hasErrors()){
            model.addAttribute("successMessage", "Oops, Something went wrong");
            return "rate";

        }else{
            if(rate1!=null) {
                rate1.setUser(lawyer);
                rateService.save(rate1);
            }
            rate.setUser(lawyer);
            rateService.save(rate);
            redirectAttributes.addFlashAttribute("successMessage", "Rate Saved!");
            return "redirect:/home";
        }
    }



    @GetMapping("/getAllMatters/{name}") @ResponseBody
    public List<Matter> getLawyers(@PathVariable(value = "name")String name){
        List<Matter> matterList = matterService.findMatterByNameLike(name);
        return matterList;
    }


    public Long getLastCaseNumber() {

        List<Case> caseList = caseService.findAllCases();
        if(caseList==null){
            return 1L;
        }
        else {
            if(caseList.size()==0){
                return 0L;
            }else{
                return caseList.get(caseList.size()-1).getId();
            }

        }
    }
}
