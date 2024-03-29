package com.UHK.garbagecollectors.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Landfill {

    @Id
    @GeneratedValue
    private int id;
    private int capacity;

    @OneToMany(mappedBy = "homeLandfill")
    @JsonManagedReference
    private List<GTruck> stationedTrucks = new ArrayList<>();

    @OneToOne
    private Location location;

    public Location getLocation() {return location;}

    public void setLocation(Location location) {this.location = location;}

    public List<GTruck> getStationedTrucks() {
        return stationedTrucks;
    }

    public void setStationedTrucks(List<GTruck> stationedTrucks) {
        this.stationedTrucks = stationedTrucks;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id;}

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
