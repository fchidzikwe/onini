package com.fortune.service;


import com.fortune.model.Role;

import java.util.List;

/**
 * Created by micnice on 7/4/17.
 */


public interface RoleService {

  Role findByRole(String role);

  List<Role> findAll();
}
