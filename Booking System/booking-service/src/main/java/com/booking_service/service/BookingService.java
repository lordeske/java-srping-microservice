package com.booking_service.service;

import com.booking_service.client.InventoryClient;
import com.booking_service.entity.Booking;
import com.booking_service.entity.Customer;
import com.booking_service.event.BookingEvent;
import com.booking_service.event.OrderCreatedEvent;
import com.booking_service.mail.EmailService;
import com.booking_service.repository.BookingRepository;
import com.booking_service.repository.CustomerRepository;
import com.booking_service.request.BookingRequest;
import com.booking_service.response.BookingResponse;
import com.booking_service.response.BookingStatusResponse;
import com.booking_service.response.EventResponse;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class BookingService {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private KafkaTemplate<String, BookingEvent> kafkaTemplate;

    @Autowired
    private EmailService emailService;





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

        //// Kreiranje inicijalnog  Bookinga
        Booking booking = new Booking();
        booking.setCustomerId(customer.getId());
        booking.setStatus("PENDING");
        booking.setEventId(event.getEventId());
        booking.setTicketCount(bookingRequest.getTicketCount());
        booking.setTotalPrice(event.getTicketPrice().multiply(BigDecimal.valueOf(bookingRequest.getTicketCount())));
        booking.setOrderId(null);

        bookingRepository.save(booking);




        /// Kreiranje Bookinga za slanje Kafka
        BookingEvent bookingEvent = new BookingEvent(
                customer.getId(),
                booking.getId(),
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



    public Boolean cancelBooking(Long id) throws MessagingException {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Ne postojim booking sa ID: "+ id));



        if(booking.getStatus().equals("CANCELED") || booking.getStatus().equals("FAILED"))
        {
            return false;
        }

        boolean success =  inventoryClient.increaseCapacity(booking.getEventId(), booking.getTicketCount());

        if(!success)
        {
            return false;
        }

        booking.setStatus("CANCELED");
        bookingRepository.save(booking);



        Customer customer = customerRepository.findById(booking.getCustomerId())
                        .orElseThrow(()->
                                new EntityNotFoundException("Nema korisnika sa ID: "+ booking.getCustomerId()));

        emailService.sendCancelationEmail(
                booking.getOrderId(),
                customer

        );




        return true;
    }

    @KafkaListener(topics = "booking-status", groupId = "booking-service")
    public void updateBookingStatusListener(OrderCreatedEvent confirmation) throws Exception {

        Booking booking = bookingRepository.findById(confirmation.getBookingId())
                .orElseThrow(() -> new EntityNotFoundException("Booking  sa ID: " + confirmation.getBookingId() +
                        " ne postoji"));

        booking.setStatus(confirmation.getStatus());
        booking.setOrderId(confirmation.getOrderId());

        bookingRepository.save(booking);


        Customer customer = customerRepository.findById(booking.getCustomerId()).orElseThrow(
                ()-> new EntityNotFoundException("Korisnik nije pronadjen ID: " + booking.getCustomerId())
        );




        String qrContent = formatQRContent(booking, inventoryClient.getEvent(
                    booking.getEventId()),
                        customer.getName());

        emailService.sendConfirmationEmail(
                customer.getEmail(),
                    customer.getName(),
                     booking.getTotalPrice(),
                        qrContent,
                            booking.getOrderId()
        );




    }








    public BookingStatusResponse getBookingStatus(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking  sa ID: " + id +
                        " ne postoji"));

        return new BookingStatusResponse(
                booking.getId(),
                booking.getCustomerId(),
                booking.getEventId(),
                booking.getTicketCount(),
                booking.getTotalPrice(),
                booking.getStatus(),
                booking.getOrderId()
        );



    }

    public String formatQRContent(Booking booking, EventResponse event, String customerName)
    {

        String eventName = event.getEvent();
        String venue  = event.getVenue().name();



        return """
            Rezervacija potvrdjena!

            Vlasnik : %s

            Događaj: %s
           
            Mesto: %s

            Broj karata: %d
            Ukupna cena: %.2f RSD
            Booking ID: %d
            """.formatted(
                customerName,
                eventName,

                venue,
                booking.getTicketCount(),
                booking.getTotalPrice(),
                booking.getId());




    }



}
