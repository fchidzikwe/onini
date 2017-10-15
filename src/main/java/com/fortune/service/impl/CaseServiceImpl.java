package com.fortune.service.impl;


import com.fortune.model.Case;
import com.fortune.model.Client;
import com.fortune.model.Matter;
import com.fortune.repository.CaseRepository;
import com.fortune.service.CaseService;
import com.fortune.util.RateFormater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseServiceImpl implements CaseService {
    @Autowired
    CaseRepository caseRepository;

    @Override
    public List<Case> findAllCases() {
        return caseRepository.findAll();
    }

    @Override
    public List<Case> findAllCasesByMatter(Matter matter) {
        return caseRepository.findCaseByMatter(matter);
    }

    @Override
    public List<Case> findAllCasesByClient(Client client) {
        return caseRepository.findByClient(client);
    }

    @Override
    public List<Case> findAllByMatter(Matter matter) {
        return caseRepository.findAllByMatter(matter);
    }

    @Override
    public List<Case> findAllCasesWithoutReqisition(Boolean aBoolean) {
        return caseRepository.findCaseByRequisitionmade(aBoolean);
    }

    @Override
    public List<Case> findAllCasesWithoutReqisitionAndClient(Boolean aBoolean, Client client) {
        return caseRepository.findCaseByRequisitionmadeAndClient(aBoolean,client);
    }

    @Override
    public Case findCaseById(Long id) {
        return caseRepository.findOne(id);
    }

    @Override
    public void save(Case aCase) {

        caseRepository.save(aCase);
    }

    @Override
    public void delete(Case aCase) {
        caseRepository.delete(aCase);
    }
}
