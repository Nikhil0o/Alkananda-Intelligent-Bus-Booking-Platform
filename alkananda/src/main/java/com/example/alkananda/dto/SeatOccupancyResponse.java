package com.example.alkananda.dto;

public class SeatOccupancyResponse {

    private final Long tripId;
    private final String busNumber;
    private final long totalSeats;
    private final long bookedSeats;
    private final double occupancyPercentage;

    public SeatOccupancyResponse(
            Long tripId,
            String busNumber,
            long totalSeats,
            long bookedSeats) {

        this.tripId = tripId;
        this.busNumber = busNumber;
        this.totalSeats = totalSeats;
        this.bookedSeats = bookedSeats;

        this.occupancyPercentage =
                totalSeats == 0
                        ? 0
                        : ((double) bookedSeats / totalSeats) * 100;
    }

    public Long getTripId() {
        return tripId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public long getTotalSeats() {
        return totalSeats;
    }

    public long getBookedSeats() {
        return bookedSeats;
    }

    public double getOccupancyPercentage() {
        return occupancyPercentage;
    }
}