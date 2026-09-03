package com.example.alkananda.service;

import com.example.alkananda.dto.TripRequest;
import com.example.alkananda.dto.TripResponse;
import com.example.alkananda.entity.*;
import com.example.alkananda.repository.BusRepository;
import com.example.alkananda.repository.RouteRepository;
import com.example.alkananda.repository.SeatRepository;
import com.example.alkananda.repository.TripRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final SeatRepository seatRepository;
    public TripService (TripRepository tripRepository,BusRepository busRepository
                        ,RouteRepository routeRepository,SeatRepository seatRepository) {
        this.tripRepository=tripRepository;
        this.busRepository=busRepository;
        this.routeRepository=routeRepository;
        this.seatRepository=seatRepository;
    }

    @Transactional
    public Trip addTrip(TripRequest request){

        Bus bus = busRepository.findById(request.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        Route route = routeRepository.findById(request.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        Trip trip = new Trip();

        trip.setBus(bus);
        trip.setRoute(route);
        trip.setTravelDate(request.getTravelDate());
        trip.setDepartureTime(request.getDepartureTime());
        trip.setArrivalTime(request.getArrivalTime());
        trip.setFare(request.getFare());
        Trip savedTrip=tripRepository.save(trip);

        for (int i = 1; i <= bus.getTotalSeats(); i++) {

            Seat seat = new Seat();

            seat.setTrip(savedTrip);
            seat.setSeatNumber(i);
            seat.setStatus(SeatStatus.AVAILABLE);

            seatRepository.save(seat);
        }

        return savedTrip;
    }
    public Page<TripResponse> searchTrips(
            String source,
            String destination,
            LocalDate date,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Trip> trips = tripRepository.searchTrips(
                source,
                destination,
                date,
                pageable
        );

        return trips.map(trip -> {

            long totalSeats = seatRepository.countByTripIdAndStatus(
                    trip.getId(),
                    SeatStatus.AVAILABLE
            );

            long bookedSeats = seatRepository.countByTripIdAndStatus(
                    trip.getId(),
                    SeatStatus.BOOKED
            );

            long availableSeats = totalSeats;

            TripResponse response = new TripResponse(
                    trip.getId(),
                    trip.getBus().getId(),
                    trip.getBus().getBusNumber(),
                    trip.getRoute().getId(),
                    trip.getRoute().getSource(),
                    trip.getRoute().getDestination(),
                    trip.getTravelDate(),
                    trip.getDepartureTime(),
                    trip.getArrivalTime(),
                    trip.getFare()
            );

            response.setTotalSeats((int) (totalSeats + bookedSeats));
            response.setAvailableSeats(availableSeats);

            return response;
        });
    }
    public Page<TripResponse> getTrips(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Trip> trips = tripRepository.findAll(pageable);

        return trips.map(trip -> {

            long availableSeats = seatRepository.countByTripIdAndStatus(
                    trip.getId(),
                    SeatStatus.AVAILABLE
            );

            long bookedSeats = seatRepository.countByTripIdAndStatus(
                    trip.getId(),
                    SeatStatus.BOOKED
            );

            TripResponse response = new TripResponse(
                    trip.getId(),
                    trip.getBus().getId(),
                    trip.getBus().getBusNumber(),
                    trip.getRoute().getId(),
                    trip.getRoute().getSource(),
                    trip.getRoute().getDestination(),
                    trip.getTravelDate(),
                    trip.getDepartureTime(),
                    trip.getArrivalTime(),
                    trip.getFare()
            );

            response.setTotalSeats((int) (availableSeats + bookedSeats));
            response.setAvailableSeats(availableSeats);

            return response;
        });
    }

}
