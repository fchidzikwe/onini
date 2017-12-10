package com.fortune.service;

import com.fortune.model.Case;
import com.fortune.model.Client;
import com.fortune.model.Matter;

import java.util.List;

public interface CaseService{


    List<Case> findAllCases();

    List<Case> findAllCasesByMatter(Matter matter);

    List<Case> findAllCasesByClient(Client client);

    List<Case> findAllByMatter(Matter matter);

   // List<Case> findAllCasesWithoutReqisition(Boolean aBoolean);

   // List<Case> findAllCasesWithoutReqisitionAndClient(Boolean  aBoolean, Client client);


    Case findCaseById(Long id);

    void save(Case aCase);


    void delete(Case  aCase);





}
