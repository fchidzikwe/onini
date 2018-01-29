package com.fortune.service.impl;


import com.fortune.model.*;
import com.fortune.repository.RequisitionRepository;
import com.fortune.service.RequisitionService;
import com.fortune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisitionServiceImpl implements RequisitionService {

    @Autowired
    RequisitionRepository requisitionRepository;

    @Autowired
    UserService userService;

    @Override
    public void save(Requisition requisition) {
        requisition.setView(0);
        requisitionRepository.save(requisition);
    }

    @Override
    public void saveRequisitionWithoutChangingView(Requisition requisition) {
        requisitionRepository.save(requisition);
    }




    @Override
    public Requisition findByID(Long id) {
        return requisitionRepository.findOne(id);
    }

    @Override
    public Long getLastRequisitionId() {
        return null;
    }

    @Override
    public void delete(Requisition requisition) {
        requisitionRepository.delete(requisition);
    }

    @Override
    public List<Requisition> findAllRequisition() {
        return requisitionRepository.findAll();
    }

    @Override
    public Requisition findByExpense(Expense expense) {
        return requisitionRepository.findRequisitionByExpense(expense);
    }



    @Override
    public Requisition findByClient(Client client) {
        return requisitionRepository.findRequisitionByClient(client);
    }

    @Override
    public List<Requisition> findRequisitionByACase(Case aCase) {
        return requisitionRepository.findRequisitionByACase(aCase);
    }

    @Override
    public List<Requisition> findRequisitionMadeByLawyer(User user) {
        return requisitionRepository.findRequisitionsByLawyer(user);
    }

    @Override
    public List<Requisition> findRequisitionsByStatusAndLawyer(RequisitionStatus requisitionStatus) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         User lawyer = userService.findUserByEmail(authentication.getName());
        return requisitionRepository.findRequisitionByStatusAndMadeby(requisitionStatus, lawyer);
    }

    @Override
    public List<Requisition> findRequisitionByStatusIsNotAndMadeby(RequisitionStatus requisitionStatus, Integer read) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User lawyer = userService.findUserByEmail(authentication.getName());
        return requisitionRepository.findRequisitionByStatusIsNotAndMadebyAndView(requisitionStatus, lawyer,read);
    }

    @Override
    public List<Requisition> findRequisitionByRequisitionStatus(RequisitionStatus requisitionStatus) {
        return requisitionRepository.findRequisitionByStatus(requisitionStatus);
    }

    @Override
    public List<Requisition> findRequisitionByStatusAndView(RequisitionStatus status, Integer view) {
        return requisitionRepository.findRequisitionByStatusAndView(status,view);
    }
}
