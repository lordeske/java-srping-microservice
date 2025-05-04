package com.inventory.mapper;


import com.inventory.entity.Event;
import com.inventory.entity.Venue;
import com.inventory.response.EventInventoryResponse;
import com.inventory.response.LocationInventoryResponse;
import org.springframework.stereotype.Service;

@Service
public class InventoryMapper {


    public EventInventoryResponse inEventResponse(Event event)
    {
        return EventInventoryResponse.builder()

                .eventId(event.getId())
                .capacity(event.getTotalCapacity())
                .venue(event.getVenue())
                .ticketPrice(event.getTicketPrice())
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
