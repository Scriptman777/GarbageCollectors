package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.GTruck;
import com.UHK.garbagecollectors.repos.GTruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GarbageTruckService {
    private GTruckRepository repo;

    @Autowired
    public GarbageTruckService(GTruckRepository repo){
        this.repo = repo;
    }

    public void add(GTruck newObj) {
        repo.save(newObj);
    }

    public List<GTruck> getGarbageTrucks() {
        return Collections.unmodifiableList(this.repo.findAll());
    }
}
