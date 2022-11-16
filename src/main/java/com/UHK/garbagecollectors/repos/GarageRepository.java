package com.UHK.garbagecollectors.repos;

import com.UHK.garbagecollectors.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarageRepository extends JpaRepository<Garage, Integer> {
}
