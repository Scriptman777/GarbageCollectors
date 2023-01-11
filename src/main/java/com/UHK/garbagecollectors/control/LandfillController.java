package com.UHK.garbagecollectors.control;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.Landfill;
import com.UHK.garbagecollectors.model.Location;
import com.UHK.garbagecollectors.svc.LandfillService;
import com.UHK.garbagecollectors.svc.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Array;
import java.util.List;

@Controller
public class LandfillController {

    private LocationService locationService;
    private LandfillService landfillService;

    public LandfillController(LocationService lcs, LandfillService lfs) {
        this.locationService = lcs;
        this.landfillService = lfs;
    }

    @GetMapping("/skladky")
    public String skladky(Model model) {
        model.addAttribute("landfills", landfillService.getLandfills());
        model.addAttribute("testInt", 54664656);
        return "skladky";
    }

    @GetMapping("/vytvoritSkladku")
    public String vytvoritSkladku(Model model) {
        model.addAttribute("landfill", new Landfill());
        model.addAttribute("location", new Location());
        model.addAttribute("mapCenterLat", landfillService.getCenterLocation().getGPSlat());
        model.addAttribute("mapCenterLon", landfillService.getCenterLocation().getGPSlon());
        return "vytvoritSkladku";
    }

    @PostMapping("/vytvoritSkladku")
    public String vytvoritSkladkuSubmit(
            Landfill landfill,
            Location location
    ) {
        if (location.getHouseNumber().equals("---")) {
            location.setHouseNumber("");
        }
        locationService.add(location);
        landfill.setLocation(location);
        landfillService.add(landfill);
        return "redirect:/skladky";
    }

    @GetMapping("/upravaSkladky")
    public String upravaSkladky(int id, Model model) throws Exception {
        Landfill landfill = landfillService.getById(id);

        if (landfill != null) {
            model.addAttribute("landfill", landfill);
            model.addAttribute("location", landfill.getLocation());
            model.addAttribute("mapCenterLat", landfill.getLocation().getGPSlat());
            model.addAttribute("mapCenterLon", landfill.getLocation().getGPSlon());
            return "upravaSkladky";
        }
        else {
            throw new Exception("Landfill with id " + id + " does not exist");
        }
    }

    @PostMapping("/upravaSkladky")
    public String upravitSkladkuSubmit(
            int id,
            Landfill landfill,
            Location location
    ) {
        if (location.getHouseNumber().equals("---")) {
            location.setHouseNumber("");
        }
        landfill.setId(id);
        landfill.setLocation(location);

        Landfill tempLandfill = landfillService.getById(id);

        location.setId(tempLandfill.getLocation().getId());
        landfill.setLocation(location);
        locationService.add(location);
        landfillService.add(landfill);

        return "redirect:/skladky";
    }

    @RequestMapping(value = "/smazaniSkladky", method = RequestMethod.DELETE)
    public String smazaniSkladky(int id) {
        landfillService.deleteLandfillById(id);
        return "redirect:/skladky";
    }




}
