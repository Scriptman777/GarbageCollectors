package com.UHK.garbagecollectors.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Contract {

    @Id
    @GeneratedValue
    private int id;

    private Date dateCreated;
    @OneToMany(mappedBy = "contract")
    private List<GCollection> collections = new ArrayList<>();


    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<GCollection> getCollections() {
        return collections;
    }

    public void setCollections(List<GCollection> collections) {
        this.collections = collections;
    }
}
