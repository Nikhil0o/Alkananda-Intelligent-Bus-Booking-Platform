package com.example.alkananda.dto;

import com.example.alkananda.entity.BookingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingSummary {

    private Long bookingId;
    private int seatNumber;

    private String source;
    private String destination;

    private LocalDate travelDate;

    private double amount;

    private BookingStatus status;

    private LocalDateTime bookingTime;

    public Long getBookingId() {
        return bookingId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public double getAmount() {
        return amount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}