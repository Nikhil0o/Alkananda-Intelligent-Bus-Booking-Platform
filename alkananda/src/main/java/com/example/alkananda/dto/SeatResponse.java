package com.example.alkananda.dto;

import com.example.alkananda.entity.SeatStatus;

public class SeatResponse {

    private Long seatId;
    private int seatNumber;
    private SeatStatus status;

    public Long getSeatId() {
        return seatId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }
}