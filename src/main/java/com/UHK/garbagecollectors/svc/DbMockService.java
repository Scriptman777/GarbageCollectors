package com.UHK.garbagecollectors.svc;

import com.UHK.garbagecollectors.model.*;
import com.UHK.garbagecollectors.repos.*;
import org.springframework.stereotype.Service;

@Service
public class DbMockService {

    private GCanRepository gCanRepo;

    private LocationRepository locationRepo;

    private LandfillRepository landfillRepo;

    private GTruckRepository gTruckRepo;

    private GCollectionRepository gColRepo;

    public DbMockService(GCanRepository gcs, LocationRepository ls, LandfillRepository lfs, GTruckRepository gts, GCollectionRepository gcls) {
        this.gCanRepo = gcs;
        this.locationRepo = ls;
        this.landfillRepo = lfs;
        this.gTruckRepo = gts;
        this.gColRepo = gcls;
    }


    public void createMockData() {

        if (gCanRepo.findAll().size() != 0) {
            return;
        }

        GCan testCan1 = new GCan();
        testCan1.setGarbageType(GType.Bio);
        testCan1.setVolume(10);
        Location testLoc1 = new Location();
        double lat = 15.828390412854446d;
        double lon = 50.203789947631236d;
        testLoc1.setCity("Hradec Králové");
        testLoc1.setStreet("Some Street");
        testLoc1.setHouseNumber("111-Who cares");
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
        testLoc2.setHouseNumber("321-Who cares");
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

        GCan testCan4 = new GCan();
        testCan4.setGarbageType(GType.Kovy);
        testCan4.setVolume(10);
        Location testLoc4 = new Location();
        lat = 16.162888258680308d;
        lon = 50.41670457933539d;
        testLoc4.setCity("Náchod");
        testLoc4.setStreet("Test");
        testLoc4.setHouseNumber("555");
        testLoc4.setGPSlat(lat);
        testLoc4.setGPSlon(lon);
        testCan4.setLocation(testLoc4);


        Location testLoc5Landfill = new Location();
        lat = 15.919369491429734d;
        lon = 50.261773989757565d;
        testLoc5Landfill.setCity("Černilov");
        testLoc5Landfill.setStreet("Skládka");
        testLoc5Landfill.setHouseNumber("12");
        testLoc5Landfill.setGPSlat(lat);
        testLoc5Landfill.setGPSlon(lon);

        Location testLoc6Landfill = new Location();
        lat = 50.31023771825909d;
        lon = 516.159466978318072d;
        testLoc6Landfill.setCity("Dobruška");
        testLoc6Landfill.setStreet("Skládka");
        testLoc6Landfill.setHouseNumber("3");
        testLoc6Landfill.setGPSlat(lat);
        testLoc6Landfill.setGPSlon(lon);



        Landfill lf1 = new Landfill();
        lf1.setLocation(testLoc5Landfill);
        lf1.setCapacity(1000000);

        Landfill lf2 = new Landfill();
        lf2.setLocation(testLoc6Landfill);
        lf2.setCapacity(1010101);

        GTruck gTruck1 = new GTruck();
        gTruck1.setCapacity(5300);
        gTruck1.setMake("Mercedes Benz");
        gTruck1.setModel("Econic NGT 2628");
        gTruck1.setLicencePlate("9H97903");
        gTruck1.setHomeLandfill(lf1);
        lf1.getStationedTrucks().add(gTruck1);

        GTruck gTruck2 = new GTruck();
        gTruck2.setCapacity(5200);
        gTruck2.setMake("Mercedes Benz");
        gTruck2.setModel("Econic NGT 2628");
        gTruck2.setLicencePlate("1H17513");
        gTruck2.setHomeLandfill(lf1);
        lf1.getStationedTrucks().add(gTruck2);

        GTruck gTruck3 = new GTruck();
        gTruck3.setCapacity(6000);
        gTruck3.setMake("Scania");
        gTruck3.setModel("R-series 3560");
        gTruck3.setLicencePlate("5H31592");
        gTruck3.setHomeLandfill(lf2);
        lf2.getStationedTrucks().add(gTruck3);


        GCollection gCollection1 = new GCollection();
        gCollection1.setName("Test svoz 1");
        gCollection1.getCans().add(testCan1);
        gCollection1.getCans().add(testCan2);
        gCollection1.getCans().add(testCan3);
        gCollection1.setAssignedTruck(gTruck1);

        locationRepo.save(testLoc1);
        gCanRepo.save(testCan1);

        locationRepo.save(testLoc2);
        gCanRepo.save(testCan2);

        locationRepo.save(testLoc3);
        gCanRepo.save(testCan3);

        locationRepo.save(testLoc4);
        gCanRepo.save(testCan4);

        locationRepo.save(testLoc5Landfill);
        locationRepo.save(testLoc6Landfill);
        landfillRepo.save(lf1);
        landfillRepo.save(lf2);

        gTruckRepo.save(gTruck1);
        gTruckRepo.save(gTruck2);
        gTruckRepo.save(gTruck3);
        gColRepo.save(gCollection1);


    }

}
