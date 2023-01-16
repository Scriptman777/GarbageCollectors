package com.UHK.garbagecollectors;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.GType;
import com.UHK.garbagecollectors.model.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataHelpers {

    public static Location getTestLoc1() {
        Location testLoc1 = new Location();
        testLoc1.setStreet("TestStreet");
        testLoc1.setHouseNumber("123");
        testLoc1.setCity("TestCity");
        testLoc1.setGPSlon(50);
        testLoc1.setGPSlat(50);
        return testLoc1;
    }

    public static GCan getTestCan1() {
        GCan testCan1 = new GCan();
        testCan1.setLocation(getTestLoc1());
        testCan1.setVolume(120);
        testCan1.setGarbageType(GType.Bio);
        testCan1.setId(1);
        return testCan1;
    }

    public static List<GCan> getTestList() {
        ArrayList<GCan> testList = new ArrayList<>();
        testList.add(getTestCan1());
        return testList;
    }
}
