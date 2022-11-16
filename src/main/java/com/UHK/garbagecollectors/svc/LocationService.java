package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.Location;
import com.UHK.garbagecollectors.repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LocationService {
    private LocationRepository repo;

    @Autowired
    public LocationService(LocationRepository repo){
        this.repo = repo;
    }

    public void add(Location newObj) {
        repo.save(newObj);
    }
}
