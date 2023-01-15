package com.UHK.garbagecollectors.repos;

import com.UHK.garbagecollectors.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Integer> {

        @Query("SELECT DISTINCT l.city FROM Location l WHERE l.city LIKE %:city%")
        List<String> findCities(@Param("city") String city);

        @Query("SELECT DISTINCT l.street FROM Location l WHERE l.city = ?1")
        List<String> findStreetsInCity(String city);
}
