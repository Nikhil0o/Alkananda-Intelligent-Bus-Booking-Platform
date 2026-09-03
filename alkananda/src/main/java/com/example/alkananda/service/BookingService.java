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
    public BookingResponse bookSeat(
            BookingRequest request,
            String email
    ) {

        try {

            // 1. Get the currently logged-in user
            User user = userRepository.findByEmail(email)
                    .orElseThrow(
                            () -> new RuntimeException("User not found")
                    );


            // 2. Get trip
            Trip trip = tripRepository.findById(request.getTripId())
                    .orElseThrow(
                            () -> new RuntimeException("Trip not found")
                    );


            // 3. Get seat
            Seat seat = seatRepository.findById(request.getSeatId())
                    .orElseThrow(
                            () -> new RuntimeException("Seat not found")
                    );


            // 4. Check whether seat is already booked
            if (seat.getStatus() == SeatStatus.BOOKED) {

                throw new RuntimeException(
                        "Seat already booked"
                );
            }


            // 5. Make sure seat belongs to selected trip
            if (!seat.getTrip().getId().equals(trip.getId())) {

                throw new RuntimeException(
                        "Seat does not belong to this trip"
                );
            }


            // 6. Mark seat as booked
            seat.setStatus(SeatStatus.BOOKED);

            seatRepository.save(seat);


            // 7. Create booking
            Booking booking = new Booking();

            booking.setUser(user);

            booking.setTrip(trip);

            booking.setSeat(seat);

            booking.setBookingTime(
                    LocalDateTime.now()
            );

            booking.setAmount(
                    trip.getFare()
            );

            booking.setStatus(
                    BookingStatus.CONFIRMED
            );


            // 8. Save booking
            Booking savedBooking =
                    bookingRepository.save(booking);


            // 9. Create response
            BookingResponse response =
                    new BookingResponse();


            response.setBookingId(
                    savedBooking.getId()
            );

            response.setUserName(
                    user.getName()
            );

            response.setSeatNumber(
                    seat.getSeatNumber()
            );


            // 10. Route information
            response.setSource(
                    trip.getRoute().getSource()
            );

            response.setDestination(
                    trip.getRoute().getDestination()
            );


            // 11. Trip information
            response.setTravelDate(
                    trip.getTravelDate()
            );

            response.setDepartureTime(
                    trip.getDepartureTime()
            );

            response.setArrivalTime(
                    trip.getArrivalTime()
            );


            // 12. Payment/booking amount
            response.setAmount(
                    savedBooking.getAmount()
            );


            // 13. Booking time
            response.setBookingTime(
                    savedBooking.getBookingTime()
            );

            response.setBus(savedBooking.getBus());
            // 14. Return booking response
            return response;


        } catch (OptimisticLockException e) {

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
            response.setBus(booking.getBus());
            response.setAmount(booking.getAmount());
            response.setStatus(booking.getStatus());
            response.setBookingTime(booking.getBookingTime());
            response.setArrivalTime((booking.getTrip().getArrivalTime()));
            response.setDepartureTime(booking.getTrip().getDepartureTime());
            response.setBusNumber(booking.getTrip().getBus().getBusNumber());

            return response;

        }).toList();
    }
}
