package com.inventory.controller;




import com.inventory.response.EventInventoryResponse;
import com.inventory.response.LocationInventoryResponse;
import com.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {


    @Autowired
    InventoryService inventoryService;

    @GetMapping("/events")
    public ResponseEntity<List<EventInventoryResponse>> getAllEvents()
    {


       return ResponseEntity.ok(inventoryService.inventoryGetAllEvents()) ;
    }


    @GetMapping("/venue/{venueId}")
    public ResponseEntity<LocationInventoryResponse> getVenueById
            (
                    @PathVariable("venueId") Long venueId
            )
    {

        return ResponseEntity.ok(inventoryService.getVenueInformation(venueId));

    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<EventInventoryResponse> getEventById
            (@PathVariable("eventId") Long eventId)
    {

        return  ResponseEntity.ok(inventoryService.getEventById(eventId));
    }


    @PutMapping("events/{eventId}/capacity/{capacity}")
    public ResponseEntity<Boolean> updateEventCapacity
            (@PathVariable("eventId") Long eventId,
             @PathVariable("capacity") Long ticketBooked)
    {


        return ResponseEntity.ok(inventoryService.updateEventCapacity(eventId,ticketBooked));
    }


    @PostMapping("/events/increase/{eventId}/capacity/{capacity}")
    boolean increaseCapacity(
            @PathVariable("eventId") Long eventId,
            @PathVariable("capacity") Long capacity

    )
    {



        return  inventoryService.increaseCapacity(eventId, capacity);
    }






}
