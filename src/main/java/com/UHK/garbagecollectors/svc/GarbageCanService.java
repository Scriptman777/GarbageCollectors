package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.Location;
import com.UHK.garbagecollectors.repos.GCanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    public List<GCan> getGarbageCans() {
        return Collections.unmodifiableList(this.repo.findAll());
    }

    public Location getCenterLocation(){
        List<GCan> gCans = this.repo.findAll();
        double averageGpsLat;
        double latSum = 0;
        for (GCan gCan : gCans) {
            latSum += gCan.getLocation().getGPSlat();
        }
        averageGpsLat = latSum / gCans.size();

        double averageGpsLon;
        double lonSum = 0;
        for (GCan gCan : gCans) {
            lonSum += gCan.getLocation().getGPSlon();
        }
        averageGpsLon = lonSum / gCans.size();

        Location centerLocation = new Location();
        centerLocation.setGPSlat(averageGpsLat);
        centerLocation.setGPSlon(averageGpsLon);
        return centerLocation;
    }


}
