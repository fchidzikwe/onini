package com.fortune.repository;

import com.fortune.model.Case;
import com.fortune.model.Client;
import com.fortune.model.Matter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseRepository extends JpaRepository<Case, Long> {

    List<Case> findAll();

    List<Case> findByClient(Client client);

    List<Case> findCaseByMatter(Matter matter);
}
