package com.example.alkananda.controller;

import com.example.alkananda.dto.*;
import com.example.alkananda.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(
            AnalyticsService analyticsService) {

        this.analyticsService = analyticsService;
    }

    @GetMapping("/summary")
    public AnalyticsSummaryResponse getSummary() {
        return analyticsService.getSummary();
    }

    @GetMapping("/popular-routes")
    public List<RouteAnalyticsResponse> getPopularRoutes() {
        return analyticsService.getPopularRoutes();
    }

    @GetMapping("/revenue-by-route")
    public List<RevenueRouteResponse> getRevenueByRoute() {
        return analyticsService.getRevenueByRoute();
    }

    @GetMapping("/seat-occupancy")
    public List<SeatOccupancyResponse> getSeatOccupancy() {
        return analyticsService.getSeatOccupancy();
    }

    @GetMapping("/booking-trends")
    public List<BookingTrendResponse> getBookingTrends() {
        return analyticsService.getBookingTrends();
    }
}