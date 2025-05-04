package com.inventory.response;

import com.inventory.entity.Venue;
import lombok.Builder;

import java.math.BigDecimal;


@Builder
public record   EventInventoryResponse(


        Long eventId,
        String event,
      Long capacity,
      Venue venue,
      BigDecimal ticketPrice







) {






}
