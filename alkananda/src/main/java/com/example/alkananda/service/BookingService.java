package com.example.alkananda.service;

import com.example.alkananda.dto.BookingRequest;
import com.example.alkananda.dto.BookingResponse;
import com.example.alkananda.dto.BookingSummary;
import com.example.alkananda.entity.*;
import com.example.alkananda.exception.ResourceNotFoundException;
import com.example.alkananda.repository.*;
import jakarta.persistence.OptimisticLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final userRepository userRepository;
    private final TripRepository tripRepository;
    private final SeatRepository seatRepository;

    public BookingService(
            BookingRepository bookingRepository,
            userRepository userRepository,
            TripRepository tripRepository,
            SeatRepository seatRepository) {

        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
        this.seatRepository = seatRepository;
    }

    @Transactional
    public BookingResponse bookSeat(BookingRequest request) {

        try{
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Trip trip = tripRepository.findById(request.getTripId())
                    .orElseThrow(() -> new RuntimeException("Trip not found"));

            Seat seat = seatRepository.findById(request.getSeatId())
                    .orElseThrow(() -> new RuntimeException("Seat not found"));

            if (seat.getStatus() == SeatStatus.BOOKED) {
                throw new RuntimeException("Seat already booked");
            }

            if (!seat.getTrip().getId().equals(trip.getId())) {
                throw new RuntimeException("Seat does not belong to this trip");
            }

            seat.setStatus(SeatStatus.BOOKED);
            seatRepository.save(seat);

            Booking booking = new Booking();

            booking.setUser(user);
            booking.setTrip(trip);
            booking.setSeat(seat);
            booking.setBookingTime(LocalDateTime.now());
            booking.setAmount(trip.getFare());

            Booking savedBooking = bookingRepository.save(booking);
            BookingResponse response = new BookingResponse();

            response.setBookingId(savedBooking.getId());
            response.setUserName(user.getName());
            response.setSeatNumber(seat.getSeatNumber());

            response.setSource(trip.getRoute().getSource());
            response.setDestination(trip.getRoute().getDestination());

            response.setTravelDate(trip.getTravelDate());
            response.setDepartureTime(trip.getDepartureTime());
            response.setArrivalTime(trip.getArrivalTime());

            response.setAmount(savedBooking.getAmount());
            booking.setStatus(BookingStatus.CONFIRMED);

            response.setBookingTime(savedBooking.getBookingTime());

            return response;
        }catch (OptimisticLockException e) {

            throw new ResourceNotFoundException(
                    "Seat was booked by another user. Please select another seat."
            );
        }
    }

    @Transactional
    public String cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new ResourceNotFoundException("Booking already cancelled");
        }

        Seat seat = booking.getSeat();

        seat.setStatus(SeatStatus.AVAILABLE);
        seatRepository.save(seat);

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        return "Booking cancelled successfully";
    }
    public List<BookingSummary> getUserBookings(Long userId) {

        List<Booking> bookings = bookingRepository.findByUserId(userId);

        return bookings.stream().map(booking -> {

            BookingSummary response = new BookingSummary();

            response.setBookingId(booking.getId());
            response.setSeatNumber(
                    booking.getSeat().getSeatNumber()
            );

            response.setSource(
                    booking.getTrip().getRoute().getSource()
            );

            response.setDestination(
                    booking.getTrip().getRoute().getDestination()
            );

            response.setTravelDate(
                    booking.getTrip().getTravelDate()
            );

            response.setAmount(booking.getAmount());
            response.setStatus(booking.getStatus());
            response.setBookingTime(booking.getBookingTime());

            return response;

        }).toList();
    }
}
