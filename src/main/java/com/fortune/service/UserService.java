package com.fortune.service;


import com.fortune.model.Role;
import com.fortune.model.User;

import java.util.List;

public interface UserService {
  public User findUserByEmail(String email);

  public User findUserById(Long id);

  public void saveUser(User user);

  public void deleteUser(User user);

  public List<User> findUsersByRole(Role role);

  public List<User> findAll();
}
