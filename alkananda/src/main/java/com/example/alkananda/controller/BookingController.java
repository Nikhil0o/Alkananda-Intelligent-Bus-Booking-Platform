package com.example.alkananda.controller;

import com.example.alkananda.dto.BookingRequest;
import com.example.alkananda.dto.BookingResponse;
import com.example.alkananda.dto.BookingSummary;
import com.example.alkananda.entity.Booking;
import com.example.alkananda.service.BookingService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public BookingResponse bookSeat(
            @RequestBody BookingRequest request,
            Authentication authentication
    ) {
        System.out.println("Authentication name: " + authentication.getName());
        System.out.println("Authentication object: " + authentication);
        return bookingService.bookSeat(request, authentication.getName());
    }
    @DeleteMapping("/cancel/{bookingId}")
    public String cancelBooking(@PathVariable Long bookingId) {
        return bookingService.cancelBooking(bookingId);
    }
    @GetMapping("/user/{userId}")
    public List<BookingSummary> getUserBookings(
            @PathVariable Long userId) {

        return bookingService.getUserBookings(userId);
    }
}