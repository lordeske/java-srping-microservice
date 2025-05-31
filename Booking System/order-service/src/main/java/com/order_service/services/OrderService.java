package com.order_service.services;


import com.order_service.client.InventoryClient;
import com.order_service.entity.BookingStatus;
import com.order_service.entity.Order;
import com.order_service.event.BookingEvent;
import com.order_service.event.OrderCreatedEvent;
import com.order_service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private InventoryClient inventoryClient;


    @Autowired
    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;



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



        if (!isEventUpdated) {
            log.error("Nema dovoljno mesta, šaljem FAILED status");
            kafkaTemplate.send("booking-status", new OrderCreatedEvent(
                    bookingEvent.getBookingId(),
                    null,
                    BookingStatus.FAILED
            ));
            return;
        }


        log.info("Izvresn update eventaId: "+ bookingEvent.getEventId()+ " rezerisani : "+ bookingEvent.getTicketCount()+ " tiketa" );


        OrderCreatedEvent confirmation = new OrderCreatedEvent(
                bookingEvent.getBookingId(),
                order.getId(),
                BookingStatus.CONFIRMED
        );

        kafkaTemplate.send("booking-status", confirmation);
        log.info("Poslat orderCreatedEvent nazad : {}", confirmation);


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
