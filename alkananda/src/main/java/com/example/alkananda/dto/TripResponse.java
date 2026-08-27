package com.example.alkananda.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TripResponse {

    private Long tripId;

    private String busNumber;

    private String busType;

    private String source;

    private String destination;

    private LocalDate travelDate;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    private double fare;

    private int totalSeats;

    private long availableSeats;
    public TripResponse(){}
    public TripResponse(Long id, Long id1, String busNumber, Long id2, String source, String destination, LocalDate travelDate, LocalTime departureTime, LocalTime arrivalTime, double fare) {
            this.tripId=id;
            this.busNumber=busNumber;
            this.source=source;
            this.destination=destination;
            this.travelDate=travelDate;
            this.departureTime=departureTime;
            this.arrivalTime=arrivalTime;
            this.fare=fare;
    }

    public Long getTripId() {
        return tripId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public String getBusType() {
        return busType;
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

    public double getFare() {
        return fare;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public long getAvailableSeats(){
        return availableSeats;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public void setBusType(String busType) {
        this.busType = busType;
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

    public void setFare(double fare) {
        this.fare = fare;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public void setAvailableSeats(long availableSeats) {
        this.availableSeats = availableSeats;
    }
}