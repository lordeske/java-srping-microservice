package com.order_service.event;


import com.order_service.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {


    private Long bookingId;
    private Long orderId;
    private BookingStatus status; /// Failed, Confirmed to dvoje



}
