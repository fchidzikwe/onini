package com.fortune.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by fortune on 7/11/17.
 */

@Entity
@Table(name = "tariffs")
public class Tariff{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tarrifCode;

    @Column(name = "description")
    private String description;

    @JoinColumn(name = "tarrifUnit")
    @ManyToOne
    TarrifUnit tarrifUnit;

    @Column(name = "amount")
    private BigDecimal amount;

    @OneToOne
    private  PostingGroup postingGroup;

    public Long getTarrifCode() {
        return tarrifCode;
    }

    public void setTarrifCode(Long tarrifCode) {
        this.tarrifCode = tarrifCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TarrifUnit getTarrifUnit() {
        return tarrifUnit;
    }

    public void setTarrifUnit(TarrifUnit tarrifUnit) {
        this.tarrifUnit = tarrifUnit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PostingGroup getPostingGroup() {
        return postingGroup;
    }

    public void setPostingGroup(PostingGroup postingGroup) {
        this.postingGroup = postingGroup;
    }
}
