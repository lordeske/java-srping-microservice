package com.booking_service.response;


import com.booking_service.entity.Booking;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
public record BookingStatusResponse (

        Long bookingId,
        Long customerI,
        Long eventId,
        Long ticketCount,
        BigDecimal totalPrice,
        String status,
        Long orderId

){




}
