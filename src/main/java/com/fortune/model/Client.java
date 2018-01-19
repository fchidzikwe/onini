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

  @NotNull
  private ClientType clientType;


  @Column(name = "residentialAddress")
  @NotEmpty(message = "*Please provide your residentialAddress")
  private String address;

  @NotNull(message = "*Please provide your city")
  @OneToOne
  private City city;

  @Column(name = "name")
  private String name;

  @Column(name = "mobile")
  @NotEmpty(message = "*Please provide mobile number")
  private String contactNumber;




  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;


  @Column(name = "gender")
  private String gender;

  @Column(name = "nationalID")
  private String nationalID;


  @Column(name = "vatNumber")
  private String vatNumber;

  @Column(name = "active")
  private int active;


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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getVatNumber() {
    return vatNumber;
  }

  public void setVatNumber(String vatNumber) {
    this.vatNumber = vatNumber;
  }

  @Enumerated(EnumType.STRING)
  public ClientType getClientType() {
    return clientType;
  }

  public void setClientType(ClientType clientType) {
    this.clientType = clientType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getName() {

    if(getClientType().equals(ClientType.COMPANY)){
      return name;
    }else {
      return getFirstName() + " " + getLastName();
    }
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
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
    return "Client{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", clientType=" + clientType +
            ", address='" + address + '\'' +
            ", city=" + city +
            ", name='" + name + '\'' +
            ", contactNumber='" + contactNumber + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", gender='" + gender + '\'' +
            ", nationalID='" + nationalID + '\'' +
            ", vatNumber='" + vatNumber + '\'' +
            ", active=" + active +
            '}';
  }

  public void setActive(int active) {
    this.active = active;
  }


}

