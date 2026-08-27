package com.example.alkananda.service;

import com.example.alkananda.BusResponse;
import com.example.alkananda.entity.Bus;
import com.example.alkananda.repository.BusRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusService {

    private final BusRepository busRepository;

    public BusService(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    public List<BusResponse> getAllbuses(){
        List<Bus> buses=busRepository.findAll();
        return buses.stream().map(bus -> {
            BusResponse response=new BusResponse();
            response.setBusNumber(bus.getBusNumber());
            response.setBusType(bus.getBusType());
            response.setTotalSeat(bus.getTotalSeats());

            return response;
        }).toList();
    }

}
