package com.fortune.service;

import com.fortune.model.Role;
import com.fortune.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by micnice on 7/4/17.
 */

@Service
public class RoleServiceImpl implements RoleService {
  @Autowired
  @Qualifier("roleRepository")
  RoleRepository roleRepository;

  @Override
  public Role findByRole(String role) {
    return roleRepository.findByRole(role);
  }

  @Override
  public List<Role> findAll() {
    return roleRepository.findAll();
  }
}
