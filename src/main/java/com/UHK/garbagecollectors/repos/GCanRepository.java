package com.UHK.garbagecollectors.repos;

import com.UHK.garbagecollectors.model.GCan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GCanRepository extends JpaRepository<GCan, Integer> {

    List<GCan> findByLocationCityContaining(String city);


}
