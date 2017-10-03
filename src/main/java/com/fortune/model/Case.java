package com.fortune.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "casetable")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "versus")
    private String versus;

    @OneToOne
    @NotNull(message = "please select matter")
    private Matter matter;

    @NotNull(message = "Client cannot be empty")
    @JoinColumn(name = "client_client_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;

    @Column(name = "time")
    private Long timeSpent;

    @Column(name ="amount")
    private Double amount;

    @Column(name = "requisitionmade")
    private Boolean requisitionmade;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Long timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getVersus() {
        return versus;
    }

    public void setVersus(String versus) {
        this.versus = versus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Matter getMatter() {
        return matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Boolean getRequisitionmade() {
        return requisitionmade;
    }

    public void setRequisitionmade(Boolean requisitionmade) {
        this.requisitionmade = requisitionmade;
    }
}
