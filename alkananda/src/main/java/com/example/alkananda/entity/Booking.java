package com.example.alkananda.entity;

import com.example.alkananda.entity.Seat;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime bookingTime;

    private double amount;

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Trip getTrip() {
        return trip;
    }

    public Seat getSeat() {
        return seat;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public double getAmount() {
        return amount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setStatus(BookingStatus status){
        this.status=status;
    }
}