package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.repos.GCanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GarbageCanService {

    private GCanRepository gCanRepository;

    @Autowired
    public GarbageCanService(GCanRepository gCanRepository){
        this.gCanRepository = gCanRepository;
    }

    public void add(GCan newCan) {
        gCanRepository.save(newCan);
    }


}
