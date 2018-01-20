package com.fortune.service.impl;

import com.fortune.model.Role;
import com.fortune.model.User;
import com.fortune.repository.RoleRepository;
import com.fortune.repository.UserRepository;
import com.fortune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("userService")
public class UserServiceImpl implements UserService {

  @Autowired
  @Qualifier("userRepository")
  private UserRepository userRepository;

  @Autowired
  @Qualifier("roleRepository")
  private RoleRepository roleRepository;
  @Autowired

  @Qualifier("bCryptPasswordEncoder")
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public User findUserById(Long id) {
    return userRepository.findOne(id);
  }


  @Override
  public void saveUser(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setActive(1);
    userRepository.save(user);
  }

  @Override
  public void deleteUser(User user) {
    userRepository.delete(user);
  }

  @Override
  public List<User> findUsersByRole(Role role) {
    return userRepository.findUsersByRole(role);
  }

  @Override
  public List<User> findUsersByRoleAndName(Role role, String name) {

    String searchterm = "%"+name+"%";
    return userRepository.findUsersByRoleAndName(role,searchterm);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public List<User> findAllLawyers() {
    return null;
  }

  public User loggedInuser (){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user =  findUserByEmail(authentication.getName());
    return user;

  }

}
