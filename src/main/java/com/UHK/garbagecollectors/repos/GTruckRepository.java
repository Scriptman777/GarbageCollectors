package com.UHK.garbagecollectors.repos;

import com.UHK.garbagecollectors.model.GTruck;
import com.UHK.garbagecollectors.model.Landfill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GTruckRepository extends JpaRepository<GTruck, Integer> {
//    List<GTruck> findGTruckByHomeLandfill(Landfill landfill);
}
