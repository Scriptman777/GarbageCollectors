package com.UHK.garbagecollectors.control;

import com.UHK.garbagecollectors.model.*;
import com.UHK.garbagecollectors.svc.GarbageCanService;
import com.UHK.garbagecollectors.svc.GarbageTruckService;
import com.UHK.garbagecollectors.svc.LandfillService;
import com.UHK.garbagecollectors.svc.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TestController {


    private GarbageCanService garbageCanService;
    private LocationService locationService;
    private GarbageTruckService garbageTruckService;
    private LandfillService landfillService;

    @Autowired
    public TestController(GarbageCanService gcs, LocationService ls, GarbageTruckService gts, LandfillService lfs) {
        this.garbageCanService = gcs;
        this.locationService = ls;
        this.garbageTruckService = gts;
        this.landfillService = lfs;
    }






    @GetMapping("/simpleMap")
    public String simpleMap(Model model) {
        double lon = 50.2035200d;
        double lat = 15.8318111d;
        model.addAttribute("lon", lon);
        model.addAttribute("lat", lat);
        return "simpleMap";
    }

    @GetMapping("/addressFind")
    public String addressFind(Model model) {

        return "vytvoritPopelnici";
    }

    @GetMapping("/dbTest")
    public String dbTest(Model model) {

        GCan testCan = new GCan();
        testCan.setGarbageType(GType.Směsný);
        testCan.setVolume(10);

        Location testLoc = new Location();
        double lon = 50.2035200d;
        double lat = 15.8318111d;
        testLoc.setCity("Hradec Králové");
        testLoc.setStreet("Some Street");
        testLoc.setHouseNumber("123-Who cares");
        testLoc.setGPSlat(lat);
        testLoc.setGPSlon(lon);

        testCan.setLocation(testLoc);

        // Save in this order
        locationService.add(testLoc);
        garbageCanService.add(testCan);

        return "dbTest";
    }




}
