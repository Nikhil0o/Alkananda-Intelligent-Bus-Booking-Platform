package com.example.alkananda.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class TripRequest {

    private Long busId;
    private Long routeId;

    private LocalDate travelDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    private double fare;

    public Long getBusId() {
        return busId;
    }

    public Long getRouteId() {
        return routeId;
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

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
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
}