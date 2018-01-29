package com.fortune.controller;

import com.fortune.model.*;
import com.fortune.service.MessageService;
import com.fortune.service.RequisitionService;
import com.fortune.service.RoleService;
import com.fortune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fchidzikwe
 */
@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    RequisitionService requisitionService;


    @PostMapping("/sendmessage")
    public  String sendMessage(@ModelAttribute Message message, Model model, @RequestParam(value = "messageTo") Long messageTo){

        List<Requisition> notpending = requisitionService.findRequisitionByStatusIsNotAndMadeby(RequisitionStatus.PENDING, 0);
        User userTo = userService.findUserById(messageTo);
        User user = userService.loggedInuser();
        message.setMessageTo(userTo);
        message.setMessageFrom(user);
        messageService.saveMessage(message);
        Boolean notpend =true;
        model.addAttribute("notpend", notpend);
        model.addAttribute("list", notpending);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("userName", user.getName() + " "+ user.getLastName());
        model.addAttribute("messageFrom", userService.loggedInuser());
        model.addAttribute("message", new Message());
        return "requisitionlist";
    }



    @GetMapping("/readmessage/id")
    public  String readMessage( Model model, @RequestParam(value = "id") Long messageId){
        Message message = messageService.findById(messageId);
        message.setRead(1);
        messageService.save(message);
        List<Requisition> notpending = requisitionService.findRequisitionByStatusIsNotAndMadeby(RequisitionStatus.ACCEPTED, 0);
        User user = userService.loggedInuser();
        Boolean notpend =true;
        model.addAttribute("notpend", notpend);
        model.addAttribute("list", notpending);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("userName", user.getName() + " "+ user.getLastName());
        model.addAttribute("messageFrom", userService.loggedInuser());
        model.addAttribute("message", new Message());
        return "redirect:/viewinbox";
    }



    @RequestMapping(value = "/viewinbox" ,method = RequestMethod.GET)
    public String viewInbox(Model model){
        List<Requisition> notpending = requisitionService.findRequisitionByStatusIsNotAndMadeby(RequisitionStatus.PENDING, 0);

        List<Requisition> readReq = requisitionService.findRequisitionByStatusAndView(RequisitionStatus.ACCEPTED, 1);
        //get all messages to logged in user
        User user = userService.loggedInuser();
        List<Message> unreadmessages = messageService.findByMessageToAndRead(user,0);
        List<Message> readmessages = messageService.findByMessageToAndRead(user,1);
        Boolean notpend =true;


        System.out.println("************ read messages "+readmessages.size());
        model.addAttribute("readmessageList", readmessages);
        model.addAttribute("messageList", unreadmessages);
        model.addAttribute("messageListSize", unreadmessages.size());
        model.addAttribute("notpend", notpend);
        model.addAttribute("readRequisitions", readReq);
        model.addAttribute("list", notpending);
        model.addAttribute("requisitionListSize", notpending.size());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("userName", user.getName() + " "+ user.getLastName());
        model.addAttribute("messageFrom", userService.loggedInuser());
        model.addAttribute("message", new Message());
        return "requisitionlist";
    }



    @GetMapping("/getAllLawyers/{name}") @ResponseBody
    public List<LawyerDto> getLawyers(@PathVariable(value = "name")String name){
        Role role = roleService.findByRole("LAWYER");

        List<LawyerDto> lawyerDtoList = new ArrayList<>();

        List<User> userList = userService.findUsersByRoleAndName(role,name);

        if(userList!=null){
            userList.forEach(user -> {
                LawyerDto lawyerDto = new LawyerDto();

                lawyerDto.setId(user.getId());
                lawyerDto.setFullName(user.getName()+ " "+user.getLastName());
                lawyerDtoList.add(lawyerDto);

            });
        }


      return lawyerDtoList;
    }


}
