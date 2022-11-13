package com.UHK.garbagecollectors.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class GTruck extends AbstractVehicle {

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = GType.class)
    private List<GType> supportedGarbageTypes;
    private int capacity;

    @ManyToOne
    private Landfill homeLandfill;


    public List<GType> getSupportedGarbageTypes() {
        return supportedGarbageTypes;
    }

    public void setSupportedGarbageTypes(List<GType> supportedGarbageTypes) {
        this.supportedGarbageTypes = supportedGarbageTypes;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Landfill getHomeLandfill() {
        return homeLandfill;
    }

    public void setHomeLandfill(Landfill homeLandfill) {
        this.homeLandfill = homeLandfill;
    }
}
