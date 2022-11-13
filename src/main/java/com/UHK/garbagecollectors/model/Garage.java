package com.UHK.garbagecollectors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Garage {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "mainGarage")
    private List<AbstractVehicle> managedVehicles;


    public int getId() {
        return id;
    }

    public List<AbstractVehicle> getManagedVehicles() {
        return managedVehicles;
    }

    public void setManagedVehicles(List<AbstractVehicle> managedVehicles) {
        this.managedVehicles = managedVehicles;
    }
}
