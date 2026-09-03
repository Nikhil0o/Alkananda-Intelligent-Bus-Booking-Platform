package com.example.alkananda.dto;

import com.example.alkananda.entity.BookingStatus;
import com.example.alkananda.entity.Bus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BookingSummary {

    private Long bookingId;
    private int seatNumber;

    private String source;
    private String destination;

    private LocalDate travelDate;
    private Bus bus;
    private double amount;
    private String busNumber;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
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

    public Bus getBus() {
        return bus;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getBusNumber() {
        return busNumber;
    }



    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}