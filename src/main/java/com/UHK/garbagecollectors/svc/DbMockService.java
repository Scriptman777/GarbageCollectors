package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.*;
import org.springframework.stereotype.Service;

@Service
public class DbMockService {

    private GarbageCanService garbageCanService;
    private LocationService locationService;

    private LandfillService landfillService;

    private GarbageTruckService garbageTruckService;

    public DbMockService(GarbageCanService gcs, LocationService ls, LandfillService lfs, GarbageTruckService gts) {
        this.garbageCanService = gcs;
        this.locationService = ls;
        this.landfillService = lfs;
        this.garbageTruckService = gts;
    }


    public void createMockData() {
        GCan testCan1 = new GCan();
        testCan1.setGarbageType(GType.Bio);
        testCan1.setVolume(10);
        Location testLoc1 = new Location();
        double lat = 15.828390412854446d;
        double lon = 50.203789947631236d;
        testLoc1.setCity("Hradec Králové");
        testLoc1.setStreet("Some Street");
        testLoc1.setHouseNumber("123-Who cares");
        testLoc1.setGPSlat(lat);
        testLoc1.setGPSlon(lon);
        testCan1.setLocation(testLoc1);

        GCan testCan2 = new GCan();
        testCan2.setGarbageType(GType.Nebezpečný);
        testCan2.setVolume(15);
        Location testLoc2 = new Location();
        lat = 15.829356008099808d;
        lon = 50.20422257213757d;
        testLoc2.setCity("Hradec Králové");
        testLoc2.setStreet("Some Street");
        testLoc2.setHouseNumber("123-Who cares");
        testLoc2.setGPSlat(lat);
        testLoc2.setGPSlon(lon);
        testCan2.setLocation(testLoc2);

        GCan testCan3 = new GCan();
        testCan3.setGarbageType(GType.Recyklovatelný);
        testCan3.setVolume(10);
        Location testLoc3 = new Location();
        lat = 15.83006411127974d;
        lon = 50.20359080169955d;
        testLoc3.setCity("Hradec Králové");
        testLoc3.setStreet("Some Street");
        testLoc3.setHouseNumber("123-Who cares");
        testLoc3.setGPSlat(lat);
        testLoc3.setGPSlon(lon);
        testCan3.setLocation(testLoc3);

        Landfill lf1 = new Landfill();
        lf1.setLocation(testLoc3);
        lf1.setCapacity(1000000);

        GTruck gTruck1 = new GTruck();
        gTruck1.setCapacity(5300);
        gTruck1.setMake("Mercedes Benz");
        gTruck1.setModel("Econic NGT 2628");
        gTruck1.setLicencePlate("9H97903");
        gTruck1.setHomeLandfill(lf1);
        lf1.getStationedTrucks().add(gTruck1);

        if (garbageCanService.getGarbageCans().size() == 0) {
            locationService.add(testLoc1);
            garbageCanService.add(testCan1);

            locationService.add(testLoc2);
            garbageCanService.add(testCan2);

            locationService.add(testLoc3);
            garbageCanService.add(testCan3);

            landfillService.add(lf1);
            garbageTruckService.add(gTruck1);

        }
    }
}
