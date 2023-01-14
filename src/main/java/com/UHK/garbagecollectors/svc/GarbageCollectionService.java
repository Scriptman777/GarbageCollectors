package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.GCollection;
import com.UHK.garbagecollectors.repos.GCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public ArrayList<GCollection> getByIds(int[] ids) {
        // int != Integer, lists can't have int, can't convert int[] to Integer[] in a pretty way... WHY
        List<Integer> idList = Arrays.stream(ids).boxed().collect(Collectors.toList());
        return new ArrayList<>(repo.findAllById(idList));
    }
}
