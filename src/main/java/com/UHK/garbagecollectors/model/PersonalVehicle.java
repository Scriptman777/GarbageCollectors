package com.UHK.garbagecollectors.model;

import javax.persistence.Entity;

@Entity
public class PersonalVehicle extends AbstractVehicle {

    private String parkingSpace;


    public String getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(String parkingSpace) {
        this.parkingSpace = parkingSpace;
    }
}
