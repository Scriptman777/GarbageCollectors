package com.UHK.garbagecollectors.control;

import com.UHK.garbagecollectors.model.GCollection;
import com.UHK.garbagecollectors.svc.GarbageCollectionService;
import com.UHK.garbagecollectors.svc.ReportService;
import com.itextpdf.text.DocumentException;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ReportController {

    GarbageCollectionService garbageCollectionService;
    ReportService reportService;

    @Autowired
    public ReportController(GarbageCollectionService gcs, ReportService rps) {
        this.garbageCollectionService = gcs;
        this.reportService = rps;
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
    public void generatePayments(HttpServletResponse response, @RequestParam(value = "gcols") int[] selectedColIDs) throws DocumentException, IOException {
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


    @GetMapping("/sestavyZnámky")
    public String stickers() {
        return "sestavyZnamky";
    }





}
