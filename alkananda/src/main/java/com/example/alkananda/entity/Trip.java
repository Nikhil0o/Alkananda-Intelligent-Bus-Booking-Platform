package com.example.alkananda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "trips")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id", nullable = false)
    @NotNull(message = "Bus is required")
    private Bus bus;



    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;


    @FutureOrPresent(message = "Travel date cannot be in the past")
    private LocalDate travelDate;

    private LocalTime departureTime;

    private LocalTime arrivalTime;

    @DecimalMin(value = "0.0", inclusive = false,
            message = "Fare must be greater than 0")
    private double fare;

    public Trip() {
    }

    public Long getId() {
        return id;
    }

    public Bus getBus() {
        return bus;
    }

    public Route getRoute() {
        return route;
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

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public void setRoute(Route route) {
        this.route = route;
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