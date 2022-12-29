package com.UHK.garbagecollectors.repos;

import com.UHK.garbagecollectors.model.GCan;
import com.UHK.garbagecollectors.model.GCollection;
import com.UHK.garbagecollectors.model.GTruck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GCollectionRepository extends JpaRepository<GCollection, Integer> {

    List<GCollection> findAllByCansContaining(GCan can);
    List<GCollection> findAllByAssignedTruck(GTruck truck);

}
