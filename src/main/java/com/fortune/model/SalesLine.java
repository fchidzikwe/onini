package com.fortune.model;

import javax.persistence.*;

/**
 * Created by fortune on 7/14/17.
 */

@Entity
@Table(name = "salesline")
public class SalesLine {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private  Long id;

  @OneToOne
  private Transaction transaction;

  @OneToOne
  private Product product;

  @Column(name = "quantity")
  private Long quantity;

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

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
}
