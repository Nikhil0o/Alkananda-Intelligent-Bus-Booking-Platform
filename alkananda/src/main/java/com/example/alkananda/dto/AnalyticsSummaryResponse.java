package com.example.alkananda.dto;

public class AnalyticsSummaryResponse {

    private final long totalBookings;
    private final double totalRevenue;

    public AnalyticsSummaryResponse(
            long totalBookings,
            double totalRevenue) {

        this.totalBookings = totalBookings;
        this.totalRevenue = totalRevenue;
    }

    public long getTotalBookings() {
        return totalBookings;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}