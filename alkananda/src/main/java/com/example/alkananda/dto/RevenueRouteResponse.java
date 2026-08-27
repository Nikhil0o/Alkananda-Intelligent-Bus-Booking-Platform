package com.example.alkananda.dto;

public class RevenueRouteResponse {

    private final String route;
    private final double revenue;

    public RevenueRouteResponse(String route, double revenue) {
        this.route = route;
        this.revenue = revenue;
    }

    public String getRoute() {
        return route;
    }

    public double getRevenue() {
        return revenue;
    }
}