package com.example.alkananda.dto;

import java.time.LocalDate;

public class BookingTrendResponse {

    private final LocalDate date;
    private final long bookings;

    public BookingTrendResponse(LocalDate date, long bookings) {
        this.date = date;
        this.bookings = bookings;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getBookings() {
        return bookings;
    }
}