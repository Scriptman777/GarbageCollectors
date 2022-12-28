package com.UHK.garbagecollectors.model;

import javax.persistence.*;

@Entity
public class GCan {

    @Id
    @GeneratedValue
    private int id;
    private float volume;
    @Enumerated(EnumType.STRING)
    private GType garbageType;

    @OneToOne
    private Location location;

    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id;}

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public GType getGarbageType() {
        return garbageType;
    }

    public void setGarbageType(GType garbageType) {
        this.garbageType = garbageType;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return getLocation().getAddress() + " | " + getGarbageType() + " | " + getVolume() + "l" ;
    }

}
