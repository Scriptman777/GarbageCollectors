package com.UHK.garbagecollectors.repos;

import com.UHK.garbagecollectors.model.AbstractVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<AbstractVehicle, Integer> {

    // TODO: This will need some more logic than other repos
}
