package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.AbstractVehicle;
import com.UHK.garbagecollectors.repos.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class VehicleService {
    private VehicleRepository repo;

    @Autowired
    public VehicleService(VehicleRepository repo){
        this.repo = repo;
    }

    public void add(AbstractVehicle newObj) {
        repo.save(newObj);
    }
}
