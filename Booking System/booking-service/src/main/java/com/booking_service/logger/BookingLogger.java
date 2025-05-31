package com.booking_service.logger;


import com.booking_service.entity.Booking;
import com.booking_service.entity.BookingLog;
import com.booking_service.repository.BookingLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingLogger {

    @Autowired
    private BookingLogRepository bookingLogRepository;

    public void logConfirmed(Booking booking)
    {

        BookingLog bookingLog =  BookingLog.builder()

                .bookingId(booking.getId())
                .status(booking.getStatus().toString())
                .action("CONFIRMED")
                .description("Rezervacija uspesno napravljena")
                .timestamp(LocalDateTime.now())
                .build();


        bookingLogRepository.save(bookingLog);

    }


    public void logCancelation(Booking booking)
    {

        BookingLog bookingLog =  BookingLog.builder()

                .bookingId(booking.getId())
                .status(booking.getStatus().toString())
                .action("CANCELED")
                .description("Rezervacija uspesno cancelovana")
                .timestamp(LocalDateTime.now())
                .build();


        bookingLogRepository.save(bookingLog);

    }


    public void logCheckedIn(Booking booking) {
        BookingLog bookingLog = BookingLog.builder()
                .bookingId(booking.getId())
                .status(booking.getStatus().toString())
                .action("CHECKED_IN")
                .description("Korisnik je uspesno cekirao")
                .timestamp(LocalDateTime.now())
                .build();

        bookingLogRepository.save(bookingLog);
    }


    public void logCreation(Booking booking) {
        BookingLog bookingLog = BookingLog.builder()
                .bookingId(booking.getId())
                .status(booking.getStatus().toString())
                .action("PENDING")
                .description("Korisnik je uspesno kreirao Booking")
                .timestamp(LocalDateTime.now())
                .build();

        bookingLogRepository.save(bookingLog);


    }

    public void logPayment(Booking booking) {

        BookingLog bookingLog = BookingLog.builder()
                .bookingId(booking.getId())
                .bookingId(booking.getId())
                .status(booking.getStatus().toString())
                .action("PAID")
                .description("Korisnik je uspesno plation")
                .timestamp(LocalDateTime.now())
                .build();


        bookingLogRepository.save(bookingLog);

    }
}
