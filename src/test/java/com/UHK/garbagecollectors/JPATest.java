package com.UHK.garbagecollectors;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.Location;
import com.UHK.garbagecollectors.repos.GCanRepository;
import com.UHK.garbagecollectors.repos.LocationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class JPATest {

    @Autowired
    private GCanRepository gCanRepository;

    @Autowired
    private LocationRepository locationRepository;


    @Test
    void saveAndFindGcan() {

        GCan testCan = TestDataHelpers.getTestCan1();
        Location testLoc = testCan.getLocation();

        locationRepository.save(testLoc);
        gCanRepository.save(testCan);

        assertThat(gCanRepository.findAll()).isNotNull();

    }

    @Test
    void gCanSearchTests() {

        GCan testCan = TestDataHelpers.getTestCan1();
        Location testLoc = testCan.getLocation();

        locationRepository.save(testLoc);
        gCanRepository.save(testCan);

        assertThat(gCanRepository.findByLocationCityContaining("Test")).isNotEmpty();
        assertThat(gCanRepository.findByLocationCityContaining("AAAAAAA")).isEmpty();

        assertThat(gCanRepository.findByLocationCityContainingAndLocationStreetContaining("City", "Street")).isNotEmpty();
        assertThat(gCanRepository.findByLocationCityContainingAndLocationStreetContaining("Other", "String")).isEmpty();

        ArrayList<String> correctList = new ArrayList<>();
        correctList.add(testLoc.getStreet());

        ArrayList<String> wrongList = new ArrayList<>();
        wrongList.add("Lorem");

        assertThat(gCanRepository.findByLocationStreetInAndLocationCity(correctList, testLoc.getCity())).isNotEmpty();
        assertThat(gCanRepository.findByLocationStreetInAndLocationCity(wrongList, testLoc.getCity())).isEmpty();

        assertThat(gCanRepository.findByLocationStreetInAndLocationCity(correctList, "City")).isEmpty();
        assertThat(gCanRepository.findByLocationStreetInAndLocationCity(wrongList, "City")).isEmpty();

    }


}
