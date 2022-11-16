package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.repos.GCanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GarbageCanService {

    private GCanRepository repo;

    @Autowired
    public GarbageCanService(GCanRepository repo){
        this.repo = repo;
    }

    public void add(GCan newObj) {
        repo.save(newObj);
    }


}
