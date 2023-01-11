package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.*;
import com.UHK.garbagecollectors.repos.GTruckRepository;
import com.UHK.garbagecollectors.repos.LandfillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LandfillService {

    private LandfillRepository repo;
    private GTruckRepository gTruckRepo;

    @Autowired
    public LandfillService(LandfillRepository repo, GTruckRepository gTruckRepo){
        this.repo = repo;
        this.gTruckRepo = gTruckRepo;
    }

    public List<Landfill> getLandfills() {
        return Collections.unmodifiableList(repo.findAll());
    }

    public Location getCenterLocation(){
        List<Landfill> landfills = this.repo.findAll();
        double averageGpsLat;
        double latSum = 0;
        for (Landfill landfill : landfills) {
            latSum += landfill.getLocation().getGPSlat();
        }
        averageGpsLat = latSum / landfills.size();

        double averageGpsLon;
        double lonSum = 0;
        for (Landfill landfill : landfills) {
            lonSum += landfill.getLocation().getGPSlon();
        }
        averageGpsLon = lonSum / landfills.size();

        Location centerLocation = new Location();
        centerLocation.setGPSlat(averageGpsLat);
        centerLocation.setGPSlon(averageGpsLon);
        return centerLocation;
    }

    public Landfill getById(int id) {return repo.getReferenceById(id); }

    public void deleteLandfillById(int id) {
        Optional<Landfill> deletedLandfill = repo.findById(id);
        List<GTruck> gTrucks = deletedLandfill.get().getStationedTrucks();
        for (GTruck gTruck: gTrucks) {
            gTruck.unassignLandfill();
            gTruckRepo.save(gTruck);
        }
        repo.deleteById(id);
    }

    public void add(Landfill newObj) {
        repo.save(newObj);
    }
}
