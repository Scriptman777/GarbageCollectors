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

    @ManyToOne
    private GCollection collection;

    @OneToOne
    private Location location;


    public GCollection getCollection() {
        return collection;
    }

    public void setCollection(GCollection collection) {
        this.collection = collection;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

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
}
