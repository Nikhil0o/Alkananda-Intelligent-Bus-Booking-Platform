package com.example.alkananda.service;

import com.example.alkananda.dto.SeatResponse;
import com.example.alkananda.entity.Seat;
import com.example.alkananda.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public List<SeatResponse> getSeatsByTrip(Long tripId) {

        List<Seat> seats = seatRepository.findByTripId(tripId);

        return seats.stream().map(seat -> {

            SeatResponse response = new SeatResponse();

            response.setSeatId(seat.getId());
            response.setSeatNumber(seat.getSeatNumber());
            response.setStatus(seat.getStatus());

            return response;

        }).toList();
    }
}