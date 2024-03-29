package com.UHK.garbagecollectors.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class GCollection {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @ManyToMany
    private List<GCan> cans = new ArrayList<>();

    @OneToOne
    private GTruck assignedTruck;


    public int getTruckId() {
        return assignedTruck == null ? -1 : assignedTruck.getId();
    }

    public Set<String> getCities() {
        Set<String> result = new HashSet<>();

        for (GCan can: cans) {
            result.add(can.getLocation().getCity());
        }

        return result;
    }

    public GTruck getAssignedTruck() {
        return assignedTruck;
    }

    public void setAssignedTruck(GTruck assignedTruck) {
        this.assignedTruck = assignedTruck;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GCan> getCans() {
        return cans;
    }

    public void setCans(List<GCan> cans) {
        this.cans = cans;
    }

    public void removeCan(GCan deletedCan) {
        cans.remove(deletedCan);
    }

    public void unassignTruck(GTruck deletedTruck) {
        assignedTruck = null;
    }
}
