package com.UHK.garbagecollectors.repos;

import com.UHK.garbagecollectors.model.Landfill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandfillRepository extends JpaRepository<Landfill, Integer> {
}
