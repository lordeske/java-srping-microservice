package com.booking_service.service;

import com.booking_service.client.InventoryClient;
import com.booking_service.entity.Customer;
import com.booking_service.event.BookingEvent;
import com.booking_service.repository.CustomerRepository;
import com.booking_service.request.BookingRequest;
import com.booking_service.response.BookingResponse;
import com.booking_service.response.EventResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class BookingService {


    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private KafkaTemplate<String, BookingEvent> kafkaTemplate;


    public BookingResponse createBooking(BookingRequest bookingRequest) {


        Customer customer = customerRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Customer sa ID: " + bookingRequest.getUserId()
                        + " nije pronadjen"));

        EventResponse event = inventoryClient.getEvent(bookingRequest.getEventId());


        /// provjera stanja
        if(event == null)
        {
            throw new EntityNotFoundException("Event sa ID: " +bookingRequest.getEventId() +" ne postoji");
        }

        /// provjera kapaciteta
        if (event.getCapacity() < bookingRequest.getTicketCount() )
        {
            throw new RuntimeException("Izabrao si vise tiketa nego sto ima kapaciteta");

        }

        //// Kreiranje Bookinga

        BookingEvent bookingEvent = new BookingEvent(
                customer.getId(),
                event.getEventId(),
                bookingRequest.getTicketCount(),
                event.getTicketPrice().multiply(BigDecimal.valueOf(bookingRequest.getTicketCount()))
        );

        /// Slanje u Kafka za kreiranje ordera
        log.info("Slanje Kafka poruke: {}", bookingEvent);

        kafkaTemplate.send("booking" , bookingEvent);







        return  BookingResponse.builder()
                .customerId(customer.getId())
                .eventId(event.getEventId())
                .ticketCount(bookingRequest.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();



    }
}
