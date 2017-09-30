package com.fortune.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by fortune on 7/14/17.
 */

@Entity
@Table(name = "sales_header")
public class SalesHeader {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @OneToOne
  private  Transaction transaction;

  @Column(name = "branch")
  private String branch;

  @Column(name = "oderNumber")
  private Long orderNumber;

  @OneToOne
  private  Client client;

  @Enumerated(EnumType.STRING)
  private PaymethodMethod paymethodMethod;

  @OneToOne
  private User cashier;

  @Column(name ="status")
  private Boolean status;


  @Column(name ="transactionAmount")
  private BigDecimal transactionAmount;

  @Column(name ="amountEntered")
  private BigDecimal amountEntered;

  @Column(name = "reversalreason")
  private String reversalReason;

  @Column(name = "limits")
  private Long limit;


  @OneToOne
  private User reversedBy;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Transaction getTransaction() {
    return transaction;
  }

  public void setTransaction(Transaction transaction) {
    this.transaction = transaction;
  }

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public Long getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(Long orderNumber) {
    this.orderNumber = orderNumber;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public PaymethodMethod getPaymethodMethod() {
    return paymethodMethod;
  }

  public void setPaymethodMethod(PaymethodMethod paymethodMethod) {
    this.paymethodMethod = paymethodMethod;
  }

  public User getCashier() {
    return cashier;
  }

  public void setCashier(User cashier) {
    this.cashier = cashier;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  public BigDecimal getTransactionAmount() {
    return transactionAmount;
  }

  public void setTransactionAmount(BigDecimal transactionAmount) {
    this.transactionAmount = transactionAmount;
  }

  public BigDecimal getAmountEntered() {
    return amountEntered;
  }

  public void setAmountEntered(BigDecimal amountEntered) {
    this.amountEntered = amountEntered;
  }

  public String getReversalReason() {
    return reversalReason;
  }

  public void setReversalReason(String reversalReason) {
    this.reversalReason = reversalReason;
  }

  public Long getLimit() {
    return limit;
  }

  public void setLimit(Long limit) {
    this.limit = limit;
  }

  public User getReversedBy() {
    return reversedBy;
  }

  public void setReversedBy(User reversedBy) {
    this.reversedBy = reversedBy;
  }
}
