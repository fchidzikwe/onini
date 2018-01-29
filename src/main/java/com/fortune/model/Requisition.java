package com.fortune.model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by fortune on 7/14/17.
 */

@Entity
@Table(name = "requisition")
public class Requisition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Expense expense;

    @ManyToOne
    private Case aCase;

    @Column(name = "requisitiondate")
    private Date requisitionDate;


    private String requisitionNumber;


    @Column(name = "amount")
    private Double amount;

    @Column(name = "payee")
    private String payee;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RequisitionStatus status;

    @OneToOne
    private User madeby;


    public String getRequisitionNumber() {
        return requisitionNumber;
    }

    public void setRequisitionNumber(String requisitionNumber) {
        this.requisitionNumber = requisitionNumber;
    }

    @Column(name = "view")
    private Integer view;

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Case getaCase() {
        return aCase;
    }

    public void setaCase(Case aCase) {
        this.aCase = aCase;
    }

    public Date getRequisitionDate() {
        return requisitionDate;
    }

    public void setRequisitionDate(Date requisitionDate) {
        this.requisitionDate = requisitionDate;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public RequisitionStatus getStatus() {
        return status;
    }

    public void setStatus(RequisitionStatus status) {
        this.status = status;
    }

    public User getMadeby() {
        return madeby;
    }

    public void setMadeby(User madeby) {
        this.madeby = madeby;
    }


}
