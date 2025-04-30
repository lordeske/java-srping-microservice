package com.booking_service.controller;


import com.booking_service.BookingService;
import com.booking_service.request.BookingRequest;
import com.booking_service.response.BookingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BookingController {


    @Autowired
    BookingService bookingService;


    @PostMapping("/booking")
    public BookingResponse createBooking (
            @RequestBody BookingRequest bookingRequest
    )
    {



        return bookingService.createBooking(bookingRequest);

    }







}
