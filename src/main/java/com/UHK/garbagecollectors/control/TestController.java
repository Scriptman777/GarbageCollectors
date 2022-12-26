package com.UHK.garbagecollectors.control;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.GType;
import com.UHK.garbagecollectors.model.Location;
import com.UHK.garbagecollectors.svc.GarbageCanService;
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
import java.util.Optional;

@Controller
public class TestController {


    private GarbageCanService garbageCanService;
    private LocationService locationService;

    @Autowired
    public TestController(GarbageCanService gcs, LocationService ls) {
        this.garbageCanService = gcs;
        this.locationService = ls;

    }

    @GetMapping("/")
    public String test(Model model) {
        seedDB();
        model.addAttribute("lokace", locationService.getLocations());
        model.addAttribute("gcan", new GCan());
        return "test";
    }

    @GetMapping("/popelnice")
    public String popelnice(Model model) {
        model.addAttribute("gCan2", new GCan());
        model.addAttribute("gCans", garbageCanService.getGarbageCans());
        return "popelnice";
    }

    @GetMapping("/vytvoritPopelnici")
    public String vytvoritPopelnici(Model model) {
        model.addAttribute("gcan", new GCan());
        model.addAttribute("location", new Location());
        model.addAttribute("mapCenterLat", garbageCanService.getCenterLocation().getGPSlat());
        model.addAttribute("mapCenterLon", garbageCanService.getCenterLocation().getGPSlon());
        return "vytvoritPopelnici";
    }

    @PostMapping("/vytvoritPopelnici")
    public String vytvoritPopelniciSubmit(
            GCan gcan,
            Location location
    ) {
        if (location.getHouseNumber().equals("---")) {
            location.setHouseNumber("");
        }
        locationService.add(location);
        gcan.setLocation(location);
        garbageCanService.add(gcan);

        return "redirect:/popelnice";
    }

    @GetMapping("/upravaPopelnice")
    public String upravaPopelnice(int id, Model model) {
        List<GCan> gcans = garbageCanService.getGarbageCans();
        GCan gcan = new GCan();
        for (GCan gcanTmp : gcans) {
            if (gcanTmp.getId() == id){
                gcan = gcanTmp;
                break;
            }
        }
        model.addAttribute("gcan", gcan);
        model.addAttribute("location", gcan.getLocation());
        model.addAttribute("mapCenterLat", gcan.getLocation().getGPSlat());
        model.addAttribute("mapCenterLon", gcan.getLocation().getGPSlon());
        return "upravaPopelnice";
    }

    @PostMapping("/upravaPopelnice")
    public String upravitPopelniciSubmit(
            int id,
            GCan gcan,
            Location location
    ) {
        if (location.getHouseNumber().equals("---")) {
            location.setHouseNumber("");
        }
        gcan.setId(id);
        gcan.setLocation(location);

        List<GCan> gcans = garbageCanService.getGarbageCans();
        int locationId = 0;
        for (GCan gcanTmp : gcans) {
            if (gcanTmp.getId() == id){
                locationId = gcanTmp.getLocation().getId();
                break;
            }
        }
        location.setId(locationId);
        gcan.setLocation(location);
        locationService.add(location);
        garbageCanService.add(gcan);

        return "redirect:/popelnice";
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

    private void seedDB() {
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

        if (garbageCanService.getGarbageCans().size() == 0) {
            locationService.add(testLoc1);
            garbageCanService.add(testCan1);

            locationService.add(testLoc2);
            garbageCanService.add(testCan2);

            locationService.add(testLoc3);
            garbageCanService.add(testCan3);
        }
    }


}
