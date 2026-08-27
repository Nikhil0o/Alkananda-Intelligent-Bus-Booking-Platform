package com.example.alkananda.repository;

import com.example.alkananda.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AnalyticsRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT COUNT(b) FROM Booking b")
    long getTotalBookings();

    @Query("SELECT COALESCE(SUM(b.amount), 0) FROM Booking b")
    double getTotalRevenue();

    @Query("""
    SELECT CONCAT(r.source, ' → ', r.destination), COUNT(b)
    FROM Booking b
    JOIN b.trip t
    JOIN t.route r
    GROUP BY r.source, r.destination
    ORDER BY COUNT(b) DESC
""")
    List<Object[]> getPopularRoutes();

    @Query("""
    SELECT CONCAT(r.source, ' → ', r.destination),
           COALESCE(SUM(b.amount), 0)
    FROM Booking b
    JOIN b.trip t
    JOIN t.route r
    GROUP BY r.source, r.destination
    ORDER BY SUM(b.amount) DESC
""")
    List<Object[]> getRevenueByRoute();

    @Query("""
    SELECT t.id,
           b.busNumber,
           COUNT(s),
           SUM(CASE WHEN s.status = com.example.alkananda.entity.SeatStatus.BOOKED
                    THEN 1 ELSE 0 END)
    FROM Trip t
    JOIN t.bus b
    JOIN Seat s ON s.trip.id = t.id
    GROUP BY t.id, b.busNumber
""")
    List<Object[]> getSeatOccupancy();

    @Query("""
    SELECT FUNCTION('DATE', b.bookingTime), COUNT(b)
    FROM Booking b
    GROUP BY FUNCTION('DATE', b.bookingTime)
    ORDER BY FUNCTION('DATE', b.bookingTime)
""")
    List<Object[]> getBookingTrends();
}