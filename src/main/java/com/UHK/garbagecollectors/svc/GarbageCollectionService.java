package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.GCollection;
import com.UHK.garbagecollectors.repos.GCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GarbageCollectionService {

    private GCollectionRepository repo;

    public GCollection getById(int id) {return repo.getReferenceById(id); }

    public void deleteCollection(GCollection collection){
        repo.delete(collection);
    }

    public void deleteCollectionById(int id) {
        repo.deleteById(id);
    }

    @Autowired
    public GarbageCollectionService(GCollectionRepository repo){
        this.repo = repo;
    }

    public List<GCollection> getGarbageCollections() {return Collections.unmodifiableList(repo.findAll());}

    public void add(GCollection newObj) {
        repo.save(newObj);
    }
}
