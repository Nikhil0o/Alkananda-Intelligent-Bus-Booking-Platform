package com.example.alkananda.service;

import com.example.alkananda.dto.*;
import com.example.alkananda.repository.AnalyticsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AnalyticsService {

    private final AnalyticsRepository analyticsRepository;

    public AnalyticsService(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    public AnalyticsSummaryResponse getSummary() {

        long totalBookings =
                analyticsRepository.getTotalBookings();

        double totalRevenue =
                analyticsRepository.getTotalRevenue();

        return new AnalyticsSummaryResponse(
                totalBookings,
                totalRevenue
        );
    }
    public List<RouteAnalyticsResponse> getPopularRoutes() {

        List<Object[]> results =
                analyticsRepository.getPopularRoutes();

        return results.stream()
                .map(row -> new RouteAnalyticsResponse(
                        (String) row[0],
                        (Long) row[1]
                ))
                .toList();
    }

    public List<RevenueRouteResponse> getRevenueByRoute() {

        List<Object[]> results =
                analyticsRepository.getRevenueByRoute();

        return results.stream()
                .map(row -> new RevenueRouteResponse(
                        (String) row[0],
                        ((Number) row[1]).doubleValue()
                ))
                .toList();
    }

    public List<SeatOccupancyResponse> getSeatOccupancy() {

        List<Object[]> results =
                analyticsRepository.getSeatOccupancy();

        return results.stream()
                .map(row -> new SeatOccupancyResponse(
                        ((Number) row[0]).longValue(),
                        (String) row[1],
                        ((Number) row[2]).longValue(),
                        ((Number) row[3]).longValue()
                ))
                .toList();
    }

    public List<BookingTrendResponse> getBookingTrends() {

        List<Object[]> results =
                analyticsRepository.getBookingTrends();

        return results.stream()
                .map(row -> {

                    LocalDate date;

                    if (row[0] instanceof java.sql.Date) {
                        date = ((java.sql.Date) row[0]).toLocalDate();
                    } else {
                        date = (LocalDate) row[0];
                    }

                    return new BookingTrendResponse(
                            date,
                            ((Number) row[1]).longValue()
                    );
                })
                .toList();
    }
}