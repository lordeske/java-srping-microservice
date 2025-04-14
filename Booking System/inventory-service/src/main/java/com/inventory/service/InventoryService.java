package com.inventory.service;


import com.inventory.Entity.Event;
import com.inventory.Entity.Venue;
import com.inventory.mapper.InventoryMapper;
import com.inventory.repository.EventRepo;
import com.inventory.repository.VenueRepo;
import com.inventory.response.EventInventoryResponse;
import com.inventory.response.LocationInventoryResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private EventRepo eventRepo;

    @Autowired
    private VenueRepo venueRepo;

    @Autowired
    private InventoryMapper mapper;


    public List<EventInventoryResponse> inventoryGetAllEvents() {

        List<Event> events = eventRepo.findAll();

        return events.stream()
                .map(mapper::inEventResponse)
                .collect(Collectors.toList());

    }

    public LocationInventoryResponse getVenueInformation(Long venueId) {


        Venue venue = venueRepo.findById(venueId)
                .orElseThrow(() -> new EntityNotFoundException("Venue sa id: " + venueId + " nije pronadjen"));


        return mapper.inLocationInventoryResponse(venue);


    }
}
