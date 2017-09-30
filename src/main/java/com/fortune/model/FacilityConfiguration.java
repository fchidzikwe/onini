package com.fortune.model;

/**
 * @author fchidzikwe on 9/6/17
 */


import javax.persistence.*;

@Entity
@Table(name = "configuration")
public  class FacilityConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "email")
    private  String email;


    @Column(name = "profilepicture")
    private String profilePicture;

    @Column(name = "website")
    private  String website;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
