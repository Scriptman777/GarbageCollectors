package com.UHK.garbagecollectors.control;

import com.UHK.garbagecollectors.model.Landfill;
import com.UHK.garbagecollectors.svc.LandfillService;
import com.UHK.garbagecollectors.svc.LocationService;
import org.springframework.stereotype.Controller;

@Controller
public class LandfillController {

    private LocationService locationService;
    private LandfillService landfillService;

    public LandfillController(LocationService lcs, LandfillService lfs) {
        this.locationService = lcs;
        this.landfillService = lfs;
    }







}
