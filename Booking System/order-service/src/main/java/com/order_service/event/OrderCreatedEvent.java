package com.order_service.event;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {


    private Long bookingId;
    private Long orderId;
    private String status; /// Failed, Confirmed to dvoje



}
