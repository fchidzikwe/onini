package com.fortune.repository;


import com.fortune.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {
  Role findByRole(String role);

  List<Role> findAll();

}
