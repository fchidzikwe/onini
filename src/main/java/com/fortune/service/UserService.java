package com.fortune.service;


import com.fortune.model.Role;
import com.fortune.model.User;

import java.util.List;

public interface UserService {
   User findUserByEmail(String email);

   User findUserById(Long id);

   void saveUser(User user);

   void deleteUser(User user);

  List<User> findUsersByRole(Role role);

  List<User> findUsersByRoleAndName(Role role, String name);

  List<User> findAll();

    User loggedInuser();

  List<User> findAllLawyers();
}
