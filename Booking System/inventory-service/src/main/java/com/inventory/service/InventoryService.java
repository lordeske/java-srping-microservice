package com.inventory.service;


import com.inventory.entity.Event;
import com.inventory.entity.Venue;
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

    public EventInventoryResponse getEventById(Long eventId) {

        Event event = eventRepo.findById(eventId)
                .orElseThrow(()-> new EntityNotFoundException("Ne postoji Event sa ID: " + eventId));



        return mapper.inEventResponse(event);

    }

    public Boolean updateEventCapacity(Long eventId, Long ticketCount) {

        Event event = eventRepo.findById(eventId)
                .orElseThrow(()-> new EntityNotFoundException("Ne postoji Event sa ID: " + eventId));



        Long remainingCapacity  = event.getLeftCapacity() - ticketCount;

        if(remainingCapacity  < 0)
        {
            throw new RuntimeException("Izabrao si previse tiketa nema toliko kapaciteta");

        }

        event.setLeftCapacity(remainingCapacity );

        eventRepo.save(event);


        return true;
    }

    public boolean increaseCapacity(Long eventId, Long capacity) {

        Event event = eventRepo.findById(eventId)
                .orElseThrow(()-> new EntityNotFoundException("Ne postoji Event sa ID: " + eventId));


        Long increasedCapacity = event.getLeftCapacity() + capacity;

        event.setLeftCapacity(increasedCapacity);

        eventRepo.save(event);

        return true;

    }
}
