package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.Landfill;
import com.UHK.garbagecollectors.repos.LandfillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LandfillService {

    private LandfillRepository repo;

    @Autowired
    public LandfillService(LandfillRepository repo){
        this.repo = repo;
    }

    public void add(Landfill newObj) {
        repo.save(newObj);
    }
}
