package com.fortune.controller;

import com.fortune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author fchidzikwe on 9/30/17
 */
@Controller
public class ReportController {


    @Autowired
    UserService userService;

    @RequestMapping(value = "/reportform", method = RequestMethod.GET)
    public String getForm(Model model) {
        return "reports";
    }






}//Report
