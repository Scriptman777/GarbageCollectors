package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.Location;
import com.UHK.garbagecollectors.repos.GCanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        return Collections.unmodifiableList(repo.findAll());
    }

    public GCan getById(int id) {return repo.getReferenceById(id); }

    public List<GCan> getByCity(String city) {
        return repo.findByLocationCityContaining(city);
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


    public List<GCan> getByCities(List<String> cities) {
        Set<GCan> result = new HashSet<>();

        for (String city: cities) {
            List<GCan> tempList = repo.findByLocationCityContaining(city);
            result.addAll(tempList);
        }
        return new ArrayList<>(result);
    }
}
