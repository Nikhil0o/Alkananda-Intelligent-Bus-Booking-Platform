package com.example.alkananda.service;

import com.example.alkananda.dto.RouteRequest;
import com.example.alkananda.entity.Route;
import com.example.alkananda.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public RouteRequest addRoute(RouteRequest request) {
        Route route=new Route();
        route.setDestination(request.getDestination());
        route.setDistance(request.getDistance());
        route.setSource(request.getSource());
        Route saved=routeRepository.save(route);

        return request;
    }

    public List<Route> getAllRoutes(){
        return routeRepository.findAll();
    }
}