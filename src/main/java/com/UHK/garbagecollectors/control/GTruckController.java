package com.UHK.garbagecollectors.control;

import com.UHK.garbagecollectors.svc.GarbageCanService;
import com.UHK.garbagecollectors.svc.GarbageTruckService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GTruckController {

    private GarbageTruckService garbageTruckService;

    public GTruckController(GarbageTruckService gts) {
        this.garbageTruckService = gts;
    }

    @GetMapping("/svozoveVozy")
    public String svozoveVozy(Model model) {
        model.addAttribute("gTrucks", garbageTruckService.getGarbageTrucks());
        return "svozoveVozy";
    }
}
