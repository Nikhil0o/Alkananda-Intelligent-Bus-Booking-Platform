package com.example.alkananda.dto;

public class RouteAnalyticsResponse {

    private final String route;
    private final long bookings;

    public RouteAnalyticsResponse(String route, long bookings) {
        this.route = route;
        this.bookings = bookings;
    }

    public String getRoute() {
        return route;
    }

    public long getBookings() {
        return bookings;
    }
}