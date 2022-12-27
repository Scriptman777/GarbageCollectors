package com.UHK.garbagecollectors.control;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.GTruck;
import com.UHK.garbagecollectors.model.GType;
import com.UHK.garbagecollectors.model.Landfill;
import com.UHK.garbagecollectors.svc.GarbageCanService;
import com.UHK.garbagecollectors.svc.GarbageTruckService;
import com.UHK.garbagecollectors.svc.LandfillService;
import com.UHK.garbagecollectors.svc.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GTruckController {

    private GarbageTruckService garbageTruckService;
    private LandfillService landfillService;

    public GTruckController(GarbageTruckService gts, LandfillService lfs) {
        this.garbageTruckService = gts;
        this.landfillService = lfs;
    }

    @GetMapping("/svozoveVozy")
    public String svozoveVozy(Model model) {
        model.addAttribute("gTrucks", garbageTruckService.getGarbageTrucks());
        return "svozoveVozy";
    }

    @GetMapping("/vytvoritSvozovyVuz")
    public String createTruck(Model model) {
        model.addAttribute("formTruck", new GTruck());
        model.addAttribute("landfills", landfillService.getLandfills());
        return "vytvoritSvozovyVuz";
    }

    @PostMapping("/vytvoritSvozovyVuz")
    public String createTruckSubmitted(GTruck newTruck) {
        Landfill homeLf = landfillService.getById(newTruck.getHomeLandfillId());
        newTruck.setHomeLandfill(homeLf);

        garbageTruckService.add(newTruck);

        return "redirect:/svozoveVozy";
    }

    @GetMapping("/upravaSvozovehoVozu")
    public String upravaVozu(int id, Model model) throws Exception {

        GTruck gtruck = garbageTruckService.getById(id);

        if (gtruck == null) {
            throw new Exception("Truck with id " + id + " does not exist");
        }

        model.addAttribute("landfills", landfillService.getLandfills());
        model.addAttribute("formTruck", gtruck);

        return "upravaSvozovehoVozu";
    }

    @PostMapping("/upravaSvozovehoVozu")
    public String upravaVozuSubmit(int id, GTruck editedTruck) {

        Landfill homeLf = landfillService.getById(editedTruck.getHomeLandfillId());

        editedTruck.setHomeLandfill(homeLf);
        editedTruck.setId(id);
        garbageTruckService.add(editedTruck);


        return "redirect:/svozoveVozy";
    }

}
