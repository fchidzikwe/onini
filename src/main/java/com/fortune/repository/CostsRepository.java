package com.fortune.repository;

import com.fortune.model.Costs;
import com.fortune.model.Matter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CostsRepository extends JpaRepository<Costs, Long> {

    Costs findByDescription(String description);


    Costs findByName(String name);

    @Override
    List<Costs> findAll();
}
