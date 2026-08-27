package com.example.alkananda.dto;

public class RouteRequest {

    private Long id;

    private String source;

    private String destination;

    private double distance;

    public void setId(Long id) {
        this.id = id;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getDistance() {
        return distance;
    }

    public Long getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }
}
