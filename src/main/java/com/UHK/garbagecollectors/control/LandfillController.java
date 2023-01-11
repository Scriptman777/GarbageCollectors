package com.UHK.garbagecollectors.control;

import com.UHK.garbagecollectors.model.Landfill;
import com.UHK.garbagecollectors.svc.LandfillService;
import com.UHK.garbagecollectors.svc.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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







}
