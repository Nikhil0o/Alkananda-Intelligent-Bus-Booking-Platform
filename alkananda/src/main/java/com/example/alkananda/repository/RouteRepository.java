package com.example.alkananda.repository;
import com.example.alkananda.dto.RouteRequest;
import com.example.alkananda.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {

}