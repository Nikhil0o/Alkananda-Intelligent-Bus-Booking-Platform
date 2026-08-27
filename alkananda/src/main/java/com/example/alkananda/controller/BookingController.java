package com.example.alkananda.controller;

import com.example.alkananda.dto.BookingRequest;
import com.example.alkananda.dto.BookingResponse;
import com.example.alkananda.dto.BookingSummary;
import com.example.alkananda.entity.Booking;
import com.example.alkananda.service.BookingService;
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
    public BookingResponse bookSeat(@RequestBody BookingRequest request) {
        return bookingService.bookSeat(request);
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