package com.UHK.garbagecollectors;

import com.UHK.garbagecollectors.control.GCanController;
import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.GType;
import com.UHK.garbagecollectors.model.Location;
import com.UHK.garbagecollectors.svc.GarbageCanService;
import com.UHK.garbagecollectors.svc.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static com.UHK.garbagecollectors.TestDataHelpers.getTestList;
import static com.UHK.garbagecollectors.TestDataHelpers.getTestLoc1;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GCanController.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class GCanControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GarbageCanService garbageCanService;

    @MockBean
    private LocationService locationService;

    @BeforeEach
    void setup(WebApplicationContext wac) {


        MockMvcBuilders.standaloneSetup(new GCanController(garbageCanService, locationService));

        given(this.garbageCanService.getGarbageCans()).willReturn(getTestList());
        given(this.garbageCanService.getGarbageCansWithSearch(anyString(), anyString())).willReturn(getTestList());
        given(this.garbageCanService.getCenterLocation()).willReturn(getTestLoc1());

    }

    @Test
    void viewGCanList() throws Exception {

        mockMvc.perform(get("/popelnice")).andExpect(status().isOk())
                .andExpect(model().attributeExists("gCans"))
                .andExpect(model().size(3))
                .andExpect(view().name("popelnice"));
    }

    @Test
    void viewGCanCreate() throws Exception {
        mockMvc.perform(get("/vytvoritPopelnici")).andExpect(status().isOk())
                .andExpect(view().name("vytvoritPopelnici"))
                .andExpect(model().attributeExists("gcan"))
                .andExpect(model().attributeExists("location"))
                .andExpect(view().name("vytvoritPopelnici"));
    }



}
