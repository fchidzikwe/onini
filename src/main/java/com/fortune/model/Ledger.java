package com.fortune.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by fortune on 7/14/17.
 */

@Entity
@Table(name = "ledger")
public class Ledger {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long entryNumber;

  @Column(name = "externaldocnumber")
  private Long externalDocNumber;

  @Column(name = "entrydate")
  @NotNull(message = "entry date cannot be empty")
  private Date entryDate;

  @OneToOne
  private  Statement statement;

  @Enumerated(EnumType.STRING)
  private EntryType entryType;

  @OneToOne
  private Tariff tariff;

  @Column(name = "datecreated")
  @NotNull(message = "entry date cannot be empty")
  private Date createdDate;

  @OneToOne
  private User createdBy;

  @Column(name = "reversed")
  private Boolean reversed;

  @Column(name = "reversal_reason")
  private String reversalReason;


  @Column(name = "dateOfReverse")
  private Date reverseDate;

  public Boolean getReversed() {
    return reversed;
  }

  public void setReversed(Boolean reversed) {
    this.reversed = reversed;
  }

  public String getReversalReason() {
    return reversalReason;
  }

  public void setReversalReason(String reversalReason) {
    this.reversalReason = reversalReason;
  }

  public Date getReverseDate() {
    return reverseDate;
  }

  public void setReverseDate(Date reverseDate) {
    this.reverseDate = reverseDate;
  }

  public Long getEntryNumber() {
    return entryNumber;
  }

  public void setEntryNumber(Long entryNumber) {
    this.entryNumber = entryNumber;
  }

  public Long getExternalDocNumber() {
    return externalDocNumber;
  }

  public void setExternalDocNumber(Long externalDocNumber) {
    this.externalDocNumber = externalDocNumber;
  }

  public Date getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(Date entryDate) {
    this.entryDate = entryDate;
  }

  public Statement getStatement() {
    return statement;
  }

  public void setStatement(Statement statement) {
    this.statement = statement;
  }

  public EntryType getEntryType() {
    return entryType;
  }

  public void setEntryType(EntryType entryType) {
    this.entryType = entryType;
  }

  public Tariff getTariff() {
    return tariff;
  }

  public void setTariff(Tariff tariff) {
    this.tariff = tariff;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public User getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(User createdBy) {
    this.createdBy = createdBy;
  }
}
