package com.example.alkananda.repository;

import com.example.alkananda.entity.Trip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {
    List<Trip> findByRouteSourceAndRouteDestinationAndTravelDate(
            String source,
            String destination,
            LocalDate travelDate
    );

    @Query("""
    SELECT t
    FROM Trip t
    JOIN t.route r
    WHERE r.source = :source
      AND r.destination = :destination
      AND t.travelDate = :date
""")
    Page<Trip> searchTrips(
            @Param("source") String source,
            @Param("destination") String destination,
            @Param("date") LocalDate date,
            Pageable pageable
    );
}
