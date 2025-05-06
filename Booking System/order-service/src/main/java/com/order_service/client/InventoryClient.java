package com.order_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "inventory",url = "${inventory.service.url}")
public interface InventoryClient {



    @PutMapping("event/{eventId}/capacity/{capacity}")
    ResponseEntity<Boolean> updateEventCapacity
            (@PathVariable("eventId") Long eventId,
             @PathVariable("capacity") Long ticketBooked);


}
