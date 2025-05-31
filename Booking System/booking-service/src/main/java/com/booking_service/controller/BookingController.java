package com.booking_service.controller;


import com.booking_service.entity.BookingLog;
import com.booking_service.response.BookingStatusResponse;
import com.booking_service.service.BookingLogsService;
import com.booking_service.service.BookingService;
import com.booking_service.request.BookingRequest;
import com.booking_service.response.BookingResponse;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1")
public class BookingController {


    @Autowired
    private BookingService bookingService;


    @PostMapping("/booking")
    public BookingResponse createBooking(
            @RequestBody BookingRequest bookingRequest
    ) {


        return bookingService.createBooking(bookingRequest);

    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<BookingStatusResponse> getBookingStatus(
            @PathVariable Long id
    ) {


        return ResponseEntity.ok(bookingService.getBookingStatus(id));

    }

    @DeleteMapping("/booking/{id}")
    public ResponseEntity<Boolean> cancelBooking(
            @PathVariable Long id
    ) throws MessagingException {


        return ResponseEntity.ok(bookingService.cancelBooking(id));
    }


    @PostMapping("/booking/check-in/{id}")
    public ResponseEntity<Boolean> validateBooking(
            @PathVariable Long id
    )
    {



       return ResponseEntity.ok(bookingService.validateBooking(id));
    }


    @GetMapping("/booking/pay/{id}") /// za test svthe iz linka
    public ResponseEntity<String> simulatePayment(
            @PathVariable Long id
    ) throws Exception {

        boolean success =  bookingService.simulatePayment(id);

        if (success) {
            return ResponseEntity.ok("Uspesno ste izvrsili uplatu. Vaša karta je aktivna.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Rezervacija nije potvrdjena ili je placena.");
        }
    }








}
