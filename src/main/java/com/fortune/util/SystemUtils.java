package com.fortune.util;

import com.fortune.model.User;
import com.fortune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * @author fchidzikwe
 */

@Component
public class SystemUtils {

    @Autowired
   private  UserService userService;


    public   String generateSystemNumbers(String prefix, Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User loggedInUser  = userService.findUserByEmail(authentication.getName());
        String firstNameInitial = loggedInUser.getName().substring(0,1).toUpperCase();

        String lastNameInitial = loggedInUser.getName().substring(0,1).toUpperCase();

        return prefix +" "+ firstNameInitial + lastNameInitial+ "/"+ id + "/"+ Calendar.getInstance().get(Calendar.YEAR);

    }
}
