package com.UHK.garbagecollectors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Landfill {

    @Id
    @GeneratedValue
    private int id;
    private int capacity;

    @OneToMany(mappedBy = "homeLandfill")
    private List<GTruck> stationedTrucks;


    public List<GTruck> getStationedTrucks() {
        return stationedTrucks;
    }

    public void setStationedTrucks(List<GTruck> stationedTrucks) {
        this.stationedTrucks = stationedTrucks;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
