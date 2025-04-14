package com.inventory.mapper;


import com.inventory.Entity.Event;
import com.inventory.Entity.Venue;
import com.inventory.response.EventInventoryResponse;
import com.inventory.response.LocationInventoryResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryMapper {


    public EventInventoryResponse inEventResponse(Event event)
    {
        return EventInventoryResponse.builder()

                .eventId(event.getId())
                .capacity(event.getTotalCapacity())
                .venue(event.getVenue())
                .ticketPrice(null)
                .event(event.getName())
                .build();




    }


    public LocationInventoryResponse inLocationInventoryResponse(Venue venue)
    {

        return LocationInventoryResponse.builder()

                .venueId(venue.getId())
                .totalCapacity(venue.getTotalCapacity())
                .venueName(venue.getName())
                .build();


    }







}
