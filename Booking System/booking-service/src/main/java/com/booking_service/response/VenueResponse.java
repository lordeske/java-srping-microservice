package com.booking_service.response;



import lombok.Builder;

@Builder
public record VenueResponse
        (
   Long id,


   String name,


    String address,


    Long totalCapacity




        ){
}
