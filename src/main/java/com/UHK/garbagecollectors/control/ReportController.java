package com.UHK.garbagecollectors.control;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.GCollection;
import com.UHK.garbagecollectors.svc.GarbageCanService;
import com.UHK.garbagecollectors.svc.GarbageCollectionService;
import com.UHK.garbagecollectors.svc.LocationService;
import com.UHK.garbagecollectors.svc.ReportService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ReportController {

    GarbageCollectionService garbageCollectionService;
    GarbageCanService garbageCanService;
    LocationService locationService;
    ReportService reportService;

    @Autowired
    public ReportController(GarbageCollectionService gcs, ReportService rps, LocationService lcs, GarbageCanService gcans) {
        this.garbageCollectionService = gcs;
        this.reportService = rps;
        this.locationService = lcs;
        this.garbageCanService = gcans;
    }


    @GetMapping("/sestavy")
    public String reports() {
        return "sestavy";
    }

    @GetMapping("/sestavyPlatby")
    public String payments(Model model) {
        model.addAttribute("collections", garbageCollectionService.getGarbageCollections());
        model.addAttribute("selectedCollections", new ArrayList<GCollection>());

        return "sestavyPlatby";
    }

    @PostMapping ("/sestavyPlatby")
    public void generatePayments(HttpServletResponse response, @RequestParam(value = "gcols", required = false) int[] selectedColIDs) throws IOException {

        if (selectedColIDs == null || selectedColIDs.length == 0) {
            response.sendRedirect("/sestavyPlatby");
            return;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fileName = formatter.format(date) + "-payments-" + UUID.randomUUID() + ".pdf";

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        ArrayList<GCollection> collections = garbageCollectionService.getByIds(selectedColIDs);
        InputStream pdf = reportService.generatePayments(collections);
        IOUtils.copy(pdf, response.getOutputStream());
        response.flushBuffer();
    }


    @GetMapping("/sestavyZnamky")
    public String stickers(Model model,
                           @RequestParam(value= "citySearch", required = false) String citySearch,
                           @RequestParam(value= "citySelect", required = false) String citySelect) {

        citySearch = citySearch == null ? "" : citySearch;
        citySelect = citySelect == null ? "" : citySelect;

        List<String> cities = citySearch.isEmpty() ? new ArrayList<>() : locationService.searchCities(citySearch);

        System.out.println(cities);

        List<String> streets = citySelect.isEmpty() ? new ArrayList<>() : locationService.getStreetsInCity(citySelect);

        model.addAttribute("streets", streets);

        model.addAttribute("cities", cities);

        model.addAttribute("citySearch", citySearch);
        model.addAttribute("citySelect", citySelect);

        return "sestavyZnamky";
    }

    @PostMapping("/sestavyZnamky")
    public void stickerReport(HttpServletResponse response, String[] streetsSelect) throws IOException {
        List<GCan> cans = garbageCanService.getByStreets(Arrays.asList(streetsSelect));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String fileName = formatter.format(date) + "-stickers-" + UUID.randomUUID() + ".xlsx";

        response.setContentType("application/ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        InputStream xls = reportService.generateStickers(cans);
        IOUtils.copy(xls, response.getOutputStream());
        response.flushBuffer();

    }





}
