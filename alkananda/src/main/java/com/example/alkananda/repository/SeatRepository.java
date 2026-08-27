package com.example.alkananda.repository;

import com.example.alkananda.entity.Seat;
import com.example.alkananda.entity.SeatStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    List<Seat> findByTripId(Long tripId);
    long countByTripIdAndStatus(Long tripId, SeatStatus status);
}