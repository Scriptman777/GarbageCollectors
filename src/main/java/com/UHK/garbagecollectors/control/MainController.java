package com.UHK.garbagecollectors.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    public MainController() {
    }

    @GetMapping("/")
    public String main(Model model) {
        double lon = 50.2035200d;
        double lat = 15.8318111d;
        model.addAttribute("lon", lon);
        model.addAttribute("lat", lat);
        return "main";
    }

}
