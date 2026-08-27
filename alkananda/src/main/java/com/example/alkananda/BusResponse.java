package com.example.alkananda;

public class BusResponse {
    private String busNumber;
    private String busType;
    private int totalSeat;

    public int getTotalSeat() {
        return totalSeat;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public void setTotalSeat(int totalSeat) {
        this.totalSeat = totalSeat;
    }
}
