package com.fortune.repository;

import com.fortune.model.Matter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatterRepository extends JpaRepository<Matter, Long> {

    Matter findByDescription(String description);



    List<Matter> findMatterByName(String name);

    List<Matter> findMatterByNameLike(String name);


    Matter findByName(String name);

    @Override
    List<Matter> findAll();
}
