package com.order_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingEvent {

    private Long userId;
    private Long bookingId;
    private Long eventId;
    private Long ticketCount;
    private BigDecimal totalPrice;







}