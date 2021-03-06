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
    private Attendance attendance;



    @ManyToMany(cascade = CascadeType.ALL)
    private List<Costs> costs;

    @Transient
    private String costName;

    @Column(name = "price")
    private Double price;


    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne
    private User lawyer;

    private String description;

    @Column(name = "requisitionmade")
    private Boolean requisitionmade;

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

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getCostName() {

        return costs.get(0).getName();
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public Boolean getRequisitionmade() {
        return requisitionmade;
    }

    public void setRequisitionmade(Boolean requisitionmade) {
        this.requisitionmade = requisitionmade;
    }
}
