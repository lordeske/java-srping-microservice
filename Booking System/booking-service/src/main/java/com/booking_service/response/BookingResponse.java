package com.booking_service.response;



import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record BookingResponse (


        Long customerId,
        Long eventId,

        Long ticketCount,

        BigDecimal totalPrice


){


}
