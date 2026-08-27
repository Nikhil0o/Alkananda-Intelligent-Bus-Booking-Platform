package com.example.alkananda.controller;

import com.example.alkananda.dto.TripRequest;
import com.example.alkananda.dto.TripResponse;
import com.example.alkananda.entity.Trip;
import com.example.alkananda.service.TripService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trip")
public class TripController {
    private final TripService tripService;

    public TripController( TripService tripService){
        this.tripService=tripService;
    }

    @PostMapping("/add")
    public Trip addTrip(@RequestBody @Valid TripRequest request){
        return tripService.addTrip(request);
    }
    @GetMapping("/search")
    public Page<TripResponse> searchTrips(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam String date,
            @RequestParam int page,
            @RequestParam int size) {

        LocalDate travelDate = LocalDate.parse(date);

        return tripService.searchTrips(
                source,
                destination,
                travelDate,
                page,
                size
        );
    }

    @GetMapping("/get/all")
    public Page<TripResponse> getAllTrips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

            return tripService.getTrips(page, size);
    }
}
