package com.fortune.service;

import com.fortune.model.*;

import java.util.List;

public interface RequisitionService {

    void save(Requisition requisition);

    Requisition findByID(Long id);

    Long getLastRequisitionId();

    void saveRequisitionWithoutChangingView(Requisition requisition);

    void delete(Requisition requisition);

    List<Requisition> findAllRequisition();

    Requisition findByExpense(Expense expense);

    Requisition findByClient(Client client);

    List<Requisition> findRequisitionByACase(Case aCase);

    List<Requisition> findRequisitionMadeByLawyer(User user);

    List<Requisition> findRequisitionsByStatusAndLawyer(RequisitionStatus requisitionStatus);

    List<Requisition> findRequisitionByStatusIsNotAndMadeby(RequisitionStatus requisitionStatus, Integer read);

    List<Requisition> findRequisitionByRequisitionStatus(RequisitionStatus requisitionStatus);

    List<Requisition> findRequisitionByStatusAndView(RequisitionStatus status, Integer view);
}
