package com.fortune.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author fchidzikwe on 10/4/17
 */

@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Client cannot be empty")
    @JoinColumn(name = "client_client_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;

    @NotNull(message = "case cannot be null")
    @OneToOne
    @JoinColumn(name = "acase_acase_id")
    private Case aCase;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Costs> costs;

    @Column(name = "price")
    private Double price;


    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne
    private User lawyer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Case getaCase() {
        return aCase;
    }

    public void setaCase(Case aCase) {
        this.aCase = aCase;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public User getLawyer() {
        return lawyer;
    }

    public void setLawyer(User lawyer) {
        this.lawyer = lawyer;
    }

    public List<Costs> getCosts() {
        return costs;
    }

    public void setCosts(List<Costs> costs) {
        this.costs = costs;
    }
}
