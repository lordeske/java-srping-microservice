package com.booking_service.client;


import com.booking_service.response.EventResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory",url = "${inventory.service.url}")
public interface InventoryClient {



    @GetMapping("/events/{eventId}")
    EventResponse getEvent(
            @PathVariable("eventId") Long eventId
    );




}
