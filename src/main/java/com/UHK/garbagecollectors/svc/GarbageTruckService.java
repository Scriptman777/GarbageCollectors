package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.GCollection;
import com.UHK.garbagecollectors.model.GTruck;
import com.UHK.garbagecollectors.repos.GCollectionRepository;
import com.UHK.garbagecollectors.repos.GTruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GarbageTruckService {
    private GTruckRepository repo;
    private GCollectionRepository gColRepo;

    @Autowired
    public GarbageTruckService(GTruckRepository repo, GCollectionRepository gColRepo){
        this.repo = repo;
        this.gColRepo = gColRepo;
    }

    public void add(GTruck newObj) {
        repo.save(newObj);
    }

    public List<GTruck> getGarbageTrucks() {
        return Collections.unmodifiableList(repo.findAll());
    }

    public GTruck getById(int id) {return repo.getReferenceById(id); }

    public void deleteTruckById(int id) {

        GTruck deletedTruck = repo.getReferenceById(id);

        List<GCollection> collections = gColRepo.findAllByAssignedTruck(deletedTruck);

        for (GCollection gCol: collections) {
            gCol.unassignTruck(deletedTruck);
            gColRepo.save(gCol);
        }

        repo.deleteById(id);
    }
}
