package com.fortune.repository;

import com.fortune.model.Case;
import com.fortune.model.Client;
import com.fortune.model.Matter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CaseRepository extends JpaRepository<Case, Long> {

    List<Case> findAll();

    List<Case> findByClient(Client client);

    List<Case> findCaseByMatter(Matter matter);


    @Query("select c from Case c WHERE " +
           " ((:matter is null) OR c.matter = :matter) ")
    List<Case> findAllByMatter(@Param("matter") Matter matter);

//   List<Case> findCaseByRequisitionmadeAndClient(Boolean  aBoolean, Client client);


 //   List<Case> findCaseByRequisitionmade(Boolean  aBoolean);
}
