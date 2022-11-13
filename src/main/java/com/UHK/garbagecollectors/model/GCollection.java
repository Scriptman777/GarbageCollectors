package com.UHK.garbagecollectors.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class GCollection {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "collection")
    private List<GCan> cans = new ArrayList<>();

    @ManyToOne
    private Contract contract;


    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public int getId() {
        return id;
    }

    public List<GCan> getCans() {
        return cans;
    }

    public void setCans(List<GCan> cans) {
        this.cans = cans;
    }
}
