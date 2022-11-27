package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.Garage;
import com.UHK.garbagecollectors.repos.GarageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GarageService {
    private GarageRepository repo;

    @Autowired
    public GarageService(GarageRepository repo){
        this.repo = repo;
    }

    public void add(Garage newObj) {
        repo.save(newObj);
    }
}
