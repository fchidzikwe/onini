package com.fortune.repository;


import com.fortune.model.Role;
import com.fortune.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  List<User> findAll();

  List<User> findByLastName(String lastName);

  @Query("select  u from User u where u.lastName =:lastName")
  List<User> findUsingLastName(String lastName);

  @Query("select distinct u from User u join u.roles r where r = :role")
  List<User> findUsersByRole(@Param("role") Role role);

}
