package com.UHK.garbagecollectors.repos;

import com.UHK.garbagecollectors.model.GCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GCollectionRepository extends JpaRepository<GCollection, Integer> {
}
