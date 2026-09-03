package com.example.alkananda.dto;

public class BookingRequest {

//    private Long userId;
    private Long tripId;
    private Long seatId;

//    public Long getUserId() {
//        return userId;
//    }

    public Long getTripId() {
        return tripId;
    }

    public Long getSeatId() {
        return seatId;
    }

//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }
}