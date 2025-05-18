package com.booking_service.controller;


import com.booking_service.response.BookingStatusResponse;
import com.booking_service.service.BookingService;
import com.booking_service.request.BookingRequest;
import com.booking_service.response.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1")
public class BookingController {


    @Autowired
    private BookingService bookingService;


    @PostMapping("/booking")
    public BookingResponse createBooking (
            @RequestBody BookingRequest bookingRequest
    )
    {



        return bookingService.createBooking(bookingRequest);

    }

    @GetMapping("booking/{id}")
    public ResponseEntity<BookingStatusResponse>  getBookingStatus(
            @PathVariable Long id
    )
    {



       return ResponseEntity.ok(bookingService.getBookingStatus(id));

    }







}
