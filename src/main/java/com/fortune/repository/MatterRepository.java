package com.fortune.repository;

import com.fortune.model.Matter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatterRepository extends JpaRepository<Matter, Long> {

    Matter findByDescription(String description);


    Matter findByName(String name);

    @Override
    List<Matter> findAll();
}
