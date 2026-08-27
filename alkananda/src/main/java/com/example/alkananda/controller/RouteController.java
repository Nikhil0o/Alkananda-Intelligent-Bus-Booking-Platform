package com.example.alkananda.controller;

import com.example.alkananda.dto.RouteRequest;
import com.example.alkananda.entity.Route;
import com.example.alkananda.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {
    private final RouteService routeService;
    public RouteController(RouteService routeService){
        this.routeService=routeService;
    }
    @PostMapping("/add")
    public RouteRequest addRoute(@RequestBody @Valid RouteRequest request){
        return routeService.addRoute(request);
    }
    @GetMapping("/get/all")
    public List<Route> getAllRoute(){
        return routeService.getAllRoutes();
    }
}
