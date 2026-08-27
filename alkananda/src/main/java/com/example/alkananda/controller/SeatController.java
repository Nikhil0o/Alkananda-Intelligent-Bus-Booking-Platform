package com.example.alkananda.controller;

import com.example.alkananda.dto.SeatResponse;
import com.example.alkananda.entity.Seat;
import com.example.alkananda.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/trip/{tripId}")
    public List<SeatResponse> getSeatsByTrip(@PathVariable Long tripId) {
        return seatService.getSeatsByTrip(tripId);
    }
}