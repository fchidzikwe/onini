package com.fortune.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

/**
 * Created by fortune on 7/11/17.
 */



@Entity
@Table(name = "client")
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "client_id")
  private Long id;

  @Column(name = "email")
  @Email(message = "*Please provide a valid Email")
  @NotEmpty(message = "*Please provide an email")
  private String email;


  @Column(name = "gender")
  @NotEmpty(message = "*Please provide an email")
  private String gender;

  @Column(name = "nationalID")
  @NotEmpty(message = "*Please provide an email")
  private String nationalID;

  @Column(name = "skypeId")
  @NotEmpty(message = "*Please provide an email")
  private String skypeId;


  @Column(name = "residentialAddress")
  @NotEmpty(message = "*Please provide your residentialAddress")
  private String residentialAddress;


  @NotNull(message = "*Please provide your city")
  @OneToOne
  private City city;

  @Column(name = "name")
  @NotEmpty(message = "*Please provide your name")
  private String name;

  @Column(name = "mobile")
  @NotEmpty(message = "*Please provide mobile number")
  private String mobile;

  @Column(name = "last_name")
  @NotEmpty(message = "*Please provide your last name")
  private String lastName;

  @Column(name = "active")
  private int active;




    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getNationalID() {
    return nationalID;
  }

  public void setNationalID(String nationalID) {
    this.nationalID = nationalID;
  }

  public String getSkypeId() {
    return skypeId;
  }

  public void setSkypeId(String skypeId) {
    this.skypeId = skypeId;
  }




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

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getActive() {
    return active;
  }

  @Transient
  public String getIsActive(){
    if(getActive() == 1){
      return "YES";
    } else {
      return "No";
    }
  }

  @Override
  public String toString() {
    return name +
        " " + lastName ;
  }

 

  public void setActive(int active) {
    this.active = active;
  }


}

