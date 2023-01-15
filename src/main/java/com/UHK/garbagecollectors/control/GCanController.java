package com.UHK.garbagecollectors.control;

import com.UHK.garbagecollectors.model.*;
import com.UHK.garbagecollectors.svc.DbMockService;
import com.UHK.garbagecollectors.svc.GarbageCanService;
import com.UHK.garbagecollectors.svc.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GCanController {

    private final GarbageCanService garbageCanService;
    private LocationService locationService;

    private DbMockService dbMockService;

    public GCanController(GarbageCanService gcs, LocationService ls, DbMockService dbm) {
        this.garbageCanService = gcs;
        this.locationService = ls;
        this.dbMockService = dbm;
    }

    @GetMapping("/popelnice")
    public String popelnice(Model model,
                            @RequestParam(value= "citySearch", required = false) String citySearch,
                            @RequestParam(value= "streetSearch", required = false) String streetSearch) {

        List<GCan> cans = garbageCanService.getGarbageCansWithSearch(citySearch, streetSearch);

        model.addAttribute("citySearch", citySearch);
        model.addAttribute("streetSearch", streetSearch);
        model.addAttribute("gCans", cans);
        return "popelnice";
    }

    @GetMapping("/vytvoritPopelnici")
    public String vytvoritPopelnici(Model model) {
        model.addAttribute("gcan", new GCan());
        Location dummyLoc = new Location();
        dummyLoc.setHouseNumber("");
        dummyLoc.setStreet("");
        model.addAttribute("location", dummyLoc);
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
    public String upravaPopelnice(int id, Model model) throws Exception {
        GCan gcan = garbageCanService.getById(id);

        if (gcan != null) {
            model.addAttribute("gcan", gcan);
            model.addAttribute("location", gcan.getLocation());
            model.addAttribute("mapCenterLat", gcan.getLocation().getGPSlat());
            model.addAttribute("mapCenterLon", gcan.getLocation().getGPSlon());
            return "upravaPopelnice";
        }
        else {
            throw new Exception("GCan with id " + id + " does not exist");
        }

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

        GCan tempGcan = garbageCanService.getById(id);

        location.setId(tempGcan.getLocation().getId());
        gcan.setLocation(location);
        locationService.add(location);
        garbageCanService.add(gcan);

        return "redirect:/popelnice";
    }

    @RequestMapping(value = "/smazaniPopelnice", method = RequestMethod.DELETE)
    public String smazaniPopelnice(int id) {

        garbageCanService.deleteCanById(id);
        return "redirect:/popelnice";
    }
}
