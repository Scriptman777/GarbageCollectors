package com.UHK.garbagecollectors.repos;

import com.UHK.garbagecollectors.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}
