package com.fortune.model;

import javax.persistence.*;

/**
 * Created by fortune on 7/14/17.
 */

@Entity
@Table(name = "city")
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private  Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne
  private Country country;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }
}
