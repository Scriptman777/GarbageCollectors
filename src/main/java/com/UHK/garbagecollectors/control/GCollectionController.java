package com.UHK.garbagecollectors.control;

import com.UHK.garbagecollectors.model.GCollection;
import com.UHK.garbagecollectors.model.GTruck;
import com.UHK.garbagecollectors.model.GType;
import com.UHK.garbagecollectors.svc.GarbageCanService;
import com.UHK.garbagecollectors.svc.GarbageCollectionService;
import com.UHK.garbagecollectors.svc.GarbageTruckService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class GCollectionController {

    private GarbageCollectionService garbageCollectionService;
    private GarbageTruckService garbageTruckService;
    private GarbageCanService garbageCanService;

    public GCollectionController(GarbageCollectionService gcls, GarbageTruckService gts, GarbageCanService gcs) {
        this.garbageCollectionService = gcls;
        this.garbageTruckService = gts;
        this.garbageCanService = gcs;
    }

    @GetMapping("/svozy")
    public String svozy(Model model) {
        model.addAttribute("collections", garbageCollectionService.getGarbageCollections());
        return "svozy";
    }

    @GetMapping("/vytvoritSvoz")
    public String svozCreate(Model model, @RequestParam(value="city", required = false) String city) {

        if (city == null) {
            city = "";
        }
        String[] cities = city.replaceAll(" ","").split(",");
        List<String> searchCities = Arrays.asList(cities);

        model.addAttribute("gCans", garbageCanService.getByCities(searchCities));
        model.addAttribute("gTrucks", garbageTruckService.getGarbageTrucks());
        model.addAttribute("formCollection", new GCollection());
        model.addAttribute("city",city);
        return "vytvoritSvoz";
    }

    @PostMapping("/vytvoritSvoz")
    public String svozCreateSubmit(GCollection newCollection) {
        garbageCollectionService.add(newCollection);
        return "redirect:/svozy";
    }

    @GetMapping("/upravaSvozu")
    public String upravaSvozu(int id, Model model, @RequestParam(value="city", required = false) String city) throws Exception {

        GCollection gCollection = garbageCollectionService.getById(id);

        if (gCollection == null) {
            throw new Exception("Collection with id " + id + " does not exist");
        }

        if (city == null) {
            city = "";
        }
        String[] cities = city.replaceAll(" ","").split(",");
        List<String> searchCities = Arrays.asList(cities);

        model.addAttribute("gCans", garbageCanService.getByCities(searchCities));
        model.addAttribute("gTrucks", garbageTruckService.getGarbageTrucks());
        model.addAttribute("formCollection", gCollection);
        model.addAttribute("city",city);

        return "upravaSvozu";
    }

    @PostMapping("/upravaSvozu")
    public String upravaSvozuSubmit(int id, GCollection editedCollection) {

        editedCollection.setId(id);
        garbageCollectionService.add(editedCollection);

        return "redirect:/svozy";
    }




}
