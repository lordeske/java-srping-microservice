package com.inventory.response;

import com.inventory.Entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
