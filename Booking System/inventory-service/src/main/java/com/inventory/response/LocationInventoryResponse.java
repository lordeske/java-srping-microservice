package com.inventory.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record LocationInventoryResponse(

         Long venueId,
         String venueName,
         Long totalCapacity

) {
}
