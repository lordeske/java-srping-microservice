package com.booking_service.service;

import com.booking_service.client.InventoryClient;
import com.booking_service.entity.Booking;
import com.booking_service.entity.BookingLog;
import com.booking_service.entity.BookingStatus;
import com.booking_service.entity.Customer;
import com.booking_service.event.BookingEvent;
import com.booking_service.event.OrderCreatedEvent;
import com.booking_service.logger.BookingLogger;
import com.booking_service.mail.EmailService;
import com.booking_service.repository.BookingLogRepository;
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
import org.springframework.cglib.core.Local;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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


    @Autowired
    private BookingLogRepository bookingLogRepository;


    @Autowired
    private BookingLogger bookingLogger;



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
        booking.setStatus(BookingStatus.PENDING);
        booking.setEventId(event.getEventId());
        booking.setTicketCount(bookingRequest.getTicketCount());
        booking.setTotalPrice(event.getTicketPrice().multiply(BigDecimal.valueOf(bookingRequest.getTicketCount())));
        booking.setOrderId(null);
        booking.setCreatedAt(LocalDateTime.now());

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


        bookingLogger.logCreation(booking);





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



        if(booking.getStatus().equals(BookingStatus.CANCELED) || booking.getStatus().equals(BookingStatus.FAILED))
        {
            return false;
        }

        boolean success =  inventoryClient.increaseCapacity(booking.getEventId(), booking.getTicketCount());

        if(!success)
        {
            return false;
        }

        booking.setStatus(BookingStatus.CANCELED);
        bookingRepository.save(booking);



        Customer customer = customerRepository.findById(booking.getCustomerId())
                        .orElseThrow(()->
                                new EntityNotFoundException("Nema korisnika sa ID: "+ booking.getCustomerId()));

        emailService.sendCancelationEmail(
                booking.getOrderId(),
                customer

        );


        bookingLogger.logCancelation(booking);


        return true;
    }

    @KafkaListener(topics = "booking-status", groupId = "booking-service")
    public void updateBookingStatusListener(OrderCreatedEvent confirmation) throws Exception {

        Booking booking = bookingRepository.findById(confirmation.getBookingId())
                .orElseThrow(() -> new EntityNotFoundException("Booking  sa ID: " + confirmation.getBookingId() +
                        " ne postoji"));

        booking.setStatus(confirmation.getStatus());
        booking.setOrderId(confirmation.getOrderId());


        bookingLogger.logConfirmed(booking);

        bookingRepository.save(booking);


        Customer customer = customerRepository.findById(booking.getCustomerId()).orElseThrow(
                ()-> new EntityNotFoundException("Korisnik nije pronadjen ID: " + booking.getCustomerId())
        );




        emailService.sendConfirmationEmail(
                customer.getEmail(),
                    customer.getName(),
                     booking.getTotalPrice(),
                        booking.getId()
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
                booking.getStatus().toString(),
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






    public Boolean validateBooking(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking  sa ID: " + id +
                        " ne postoji"));

        if(booking.getStatus().equals(BookingStatus.CHECKED_IN))
        {

            throw new IllegalStateException("Korisnik je vec cekiran");
        }


        if(booking.getStatus() == BookingStatus.PAID)
        {

            booking.setCheckedIn(true);
            booking.setStatus(BookingStatus.CHECKED_IN);
            bookingRepository.save(booking);
            bookingLogger.logCheckedIn(booking);


            return true;


        }
        else
        {
            return false;
        }



    }

    public Boolean simulatePayment(Long id) throws Exception {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking  sa ID: " + id +
                        " ne postoji"));

        if(booking.getStatus().equals(BookingStatus.CONFIRMED))
        {

            booking.setStatus(BookingStatus.PAID);
            bookingRepository.save(booking);
            bookingLogger.logPayment(booking);

            Customer customer = customerRepository.findById(booking.getCustomerId()).orElseThrow(
                    ()-> new EntityNotFoundException("Korisnik nije pronadjen ID: " + booking.getCustomerId())
            );


            String qrContent = formatQRContent(booking, inventoryClient.getEvent(
                    booking.getEventId()),
                       customer.getName());

            emailService.sendPaidEmail(
                    customer.getEmail(),
                    customer.getName(),
                    booking.getTotalPrice(),
                    qrContent,
                    booking.getOrderId()
            );

            return true;

        }
        else
        {
            throw new Exception("Ne mozes da platis nevalidnu rezervaciju");
        }





    }



    @Scheduled(fixedRate = 60000)
    public void clearUnpaidBookings()
    {

        LocalDateTime expirationTime = LocalDateTime.now().minusMinutes(60);
        List<Booking> expiredBookings = bookingRepository.findByStatusAndCreatedAtBefore(
                BookingStatus.CONFIRMED, expirationTime);


        for (Booking booking : expiredBookings)
        {
           booking.setStatus(BookingStatus.CANCELED);
           bookingRepository.save(booking);
           bookingLogger.logCancelation(booking);


        }

    }




}
