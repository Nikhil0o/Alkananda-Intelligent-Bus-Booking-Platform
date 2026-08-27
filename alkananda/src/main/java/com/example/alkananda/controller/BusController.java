package com.example.alkananda.controller;

import com.example.alkananda.BusResponse;
import com.example.alkananda.entity.Bus;
import com.example.alkananda.service.BusService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @PostMapping("/add")
    public Bus addBus(@RequestBody @Valid Bus bus) {
        return busService.addBus(bus);
    }

    @GetMapping("/get/all")
    public List<BusResponse> getBuses(){
        return busService.getAllbuses();
    }

}
