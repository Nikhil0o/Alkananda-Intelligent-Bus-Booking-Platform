package com.example.alkananda.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="buses")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Bus number is required")

    @Column(unique = true,nullable = false)
    private String busNumber;

    @NotBlank(message = "Bus type is required")
    private String busType;
    @Min(value = 1,message = "Total seat must be Atleast 1")
    private int totalSeats;
    public Bus() {
    }

    public Long getId() {
        return id;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public String getBusType() {
        return busType;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }
}

