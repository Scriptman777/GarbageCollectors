package com.UHK.garbagecollectors.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private int id;

    private double GPSlat;
    private double GPSlon;
    private String city;
    private String street;
    private String houseNumber;

    /**
     *
     * @return Address in Mapy.cz friendly format
     */
    public String getAddress() {
        String outStr = city;
        outStr += street.length() > 0 ? ", " + street + houseNumber : "";
        return outStr;
    }


    public int getId() {
        return id;
    }

    public double getGPSlat() {
        return GPSlat;
    }

    public void setGPSlat(double GPSlat) {
        this.GPSlat = GPSlat;
    }

    public double getGPSlon() {
        return GPSlon;
    }

    public void setGPSlon(double GPSlon) {
        this.GPSlon = GPSlon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
