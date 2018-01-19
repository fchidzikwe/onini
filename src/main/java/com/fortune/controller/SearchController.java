package com.fortune.controller;

/**
 * Created by fortune on 7/29/17.
 */

import com.fortune.model.Client;
import com.fortune.model.User;
import com.fortune.repository.UserRepository;

import com.fortune.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author opalencia
 */
@Controller
@RequestMapping("/utils")
public class SearchController {



  @Autowired
  @Qualifier("userRepository")
  private UserRepository userRepository;

  @Autowired
  private ClientService clientService;

  @RequestMapping(value = "")
  public String util() {
    return "utils";
  }

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String callPojo(Model model) {
    model.addAttribute("client", new Client());
    return "searchuser";
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public String callPojo(@ModelAttribute Client client, Model model) {
    System.out.println("@@@@@@@@@@@@user passed in call user is: "+client);
    model.addAttribute("user", client);

    model.addAttribute("search", clientService.findByLastNameLike(client.getLastName()));

    return "searchuser";
  }

  @RequestMapping(value = "/getClients/", method = RequestMethod.GET)
  @ResponseBody
  public List<Client> getClientList(@RequestParam String lastName) {
    List<Client> clientList = clientService.findByLastNameLike(lastName);
    List<Client> arrayList = new ArrayList();
    arrayList.addAll(clientList);
    clientList.clear();
    for (Client c : arrayList) {
      System.out.println("@@@@@ looping for "+c);
      c.setId(c.getId());
      c.setName(null);
      c.setLastName(null);
      c.setContactNumber(null);
      c.setNationalID(null);
      clientList.add(c);
    }
    return clientList;
  }

}

