package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.GCollection;
import com.UHK.garbagecollectors.repos.GCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GarbageCollectionService {

    private GCollectionRepository repo;

    @Autowired
    public GarbageCollectionService(GCollectionRepository repo){
        this.repo = repo;
    }

    public void add(GCollection newObj) {
        repo.save(newObj);
    }
}
