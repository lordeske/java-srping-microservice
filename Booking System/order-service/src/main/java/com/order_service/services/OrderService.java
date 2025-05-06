package com.order_service.services;


import com.order_service.client.InventoryClient;
import com.order_service.entity.Order;
import com.order_service.event.BookingEvent;
import com.order_service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private InventoryClient inventoryClient;

    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent)
    {
        log.info("Dobio sam event: {}" , bookingEvent);

        /// Kreiranje Ordera u DB
        Order order = createOrderEntity(bookingEvent);
        orderRepository.save(order);


        /// Azuriranje statusa Eventa
        boolean isEventUpdated =  inventoryClient.updateEventCapacity
                (bookingEvent.getEventId(),bookingEvent.getTicketCount())
                .getBody();

        if(!isEventUpdated)
        {
           throw new RuntimeException("Nije moguce azurirati evenID: "+ bookingEvent.getEventId()+
                   " previse si karti izabrao" );

        }

        log.info("Izvresn update eventaId: "+ bookingEvent.getEventId()+ " rezerisani : "+ bookingEvent.getTicketCount()+ " tiketa" );




    }

    private Order createOrderEntity(BookingEvent bookingEvent) {

        return Order.builder()
                .customerId(bookingEvent.getUserId())

                    .eventId(bookingEvent.getEventId())
                        .ticketCount(bookingEvent.getTicketCount())
                                .totalPrice(bookingEvent.getTotalPrice())
                                        .ticketCount(bookingEvent.getTicketCount())




                .build();

    }


}
