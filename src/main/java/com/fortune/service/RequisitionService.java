package com.fortune.service;

import com.fortune.model.*;

import java.util.List;

public interface RequisitionService {

    void save(Requisition requisition);

    Requisition findByID(Long id);

    void delete(Requisition requisition);

    List<Requisition> findAllRequisition();

    Requisition findByCase(Case aCase);

    Requisition findByClient(Client client);

    List<Requisition> findRequisitionMadeByLawyer(User user);

    List<Requisition> findRequisitionsByStatusAndLawyer(RequisitionStatus requisitionStatus);

    List<Requisition> findRequisitionByStatusIsNotAndMadeby(RequisitionStatus requisitionStatus);

    List<Requisition> findRequisitionByRequisitionStatus(RequisitionStatus requisitionStatus);


}
