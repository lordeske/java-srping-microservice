package com.inventory.controller;




import com.inventory.response.EventInventoryResponse;
import com.inventory.response.LocationInventoryResponse;
import com.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {


    @Autowired
    InventoryService inventoryService;

    @GetMapping("/events")
    public ResponseEntity<List<EventInventoryResponse>> inventoryGetAllEvents()
    {


       return ResponseEntity.ok(inventoryService.inventoryGetAllEvents()) ;
    }


    @GetMapping("/venue/{venueId}")
    public ResponseEntity<LocationInventoryResponse> invntoryByVenueId
            (
                    @PathVariable("venueId") Long venueId
            )
    {

        return ResponseEntity.ok(inventoryService.getVenueInformation(venueId)) ;

    }







}
