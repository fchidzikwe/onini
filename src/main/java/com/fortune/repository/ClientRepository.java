package com.fortune.repository;

import com.fortune.model.Case;
import com.fortune.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findByEmail(String email);

    List<Client> findAll();
    

    List<Client> findByLastName(String lastName);

    List<Client> findByLastNameLike(String lastName);

    @Query("select  u from User u where u.lastName =:lastName")
    List<Client> findUsingLastName(String lastName);

    @Query("select  u from User u where u.lastName like :searchTerm or u.name like:searchTerm ")
    List<Client> findByLastNameOrNameLike(@Param("searchTerm")String searchTerm);
    
   
}
