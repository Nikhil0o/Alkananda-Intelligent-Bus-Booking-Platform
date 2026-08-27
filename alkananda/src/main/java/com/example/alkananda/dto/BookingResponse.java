package com.example.alkananda.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingResponse {

    private Long bookingId;

    private String userName;

    private int seatNumber;

    private String source;

    private String destination;

    private LocalDate travelDate;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private double amount;

    private LocalDateTime bookingTime;

    public Long getBookingId() {
        return bookingId;
    }

    public String getUserName() {
        return userName;
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

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}