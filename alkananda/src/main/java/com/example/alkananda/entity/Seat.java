package com.example.alkananda.entity;

import jakarta.persistence.*;

@Entity

@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @Version
    private long version;

    private int seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    public Seat() {
    }

    public Long getId() {
        return id;
    }

    public Trip getTrip() {
        return trip;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public long getVersion() {
        return version;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
