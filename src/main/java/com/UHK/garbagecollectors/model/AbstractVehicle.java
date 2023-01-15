package com.UHK.garbagecollectors.model;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    protected String licencePlate;
    protected String model;
    protected String make;


    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id;}

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
