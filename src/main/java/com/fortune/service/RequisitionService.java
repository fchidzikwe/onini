package com.fortune.service;

import com.fortune.model.*;

import java.util.List;

public interface RequisitionService {

    void save(Requisition requisition);

    void delete(Requisition requisition);

    List<Requisition> findAllRequisition();

    Requisition findByCase(Case aCase);

    Requisition findByClient(Client client);

    List<Requisition> findRequisitionMadeByLawyer(User user);

    List<Requisition> findRequisitionByRequisitionStatus(RequisitionStatus requisitionStatus);


}
