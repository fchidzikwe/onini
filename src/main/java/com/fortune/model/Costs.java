package com.fortune.model;


import javax.persistence.*;

/**
 * Created by fortune on 7/14/17.
 */

@Entity
@Table(name = "costs")
public class Costs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private  String name;

    @Column(name = "description")
    private  String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
