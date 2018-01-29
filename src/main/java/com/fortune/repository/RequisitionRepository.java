package com.fortune.repository;

import com.fortune.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequisitionRepository extends JpaRepository<Requisition, Long> {







    Requisition findRequisitionByExpense(Expense expense);

    @Query("select r from Requisition r where r.expense.client =:client")
    Requisition findRequisitionByClient(@Param("client") Client client);

    @Query("select r from Requisition r where r.madeby =:user")
    List<Requisition> findRequisitionsByLawyer(@Param("user") User user);

    List<Requisition> findAll();


    List<Requisition> findRequisitionByACase(Case aCase);


    @Query("select r from Requisition r where r.status =:status")
    List<Requisition>findRequisitionByStatus(@Param("status")RequisitionStatus status);

    List<Requisition> findRequisitionByStatusAndMadeby(RequisitionStatus status, User user);

    List<Requisition> findRequisitionByStatusAndView(RequisitionStatus status, Integer integer);

    List<Requisition> findRequisitionByStatusIsNotAndMadebyAndView(RequisitionStatus status, User user, Integer read);
}
