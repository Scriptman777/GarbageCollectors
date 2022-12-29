package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.Location;
import com.UHK.garbagecollectors.repos.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LocationService {
    private LocationRepository repo;
    private List<Location> locations = new ArrayList<Location>();

    @Autowired
    public LocationService(LocationRepository repo){
        this.repo = repo;
    }

    public void add(Location newObj) {
        repo.save(newObj);
    }

    public void deleteLocationById(int id) {
        repo.deleteById(id);
    }

    public List<Location> getLocations() {
        return Collections.unmodifiableList(this.repo.findAll());
    }
}
