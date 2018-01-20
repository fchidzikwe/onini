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
