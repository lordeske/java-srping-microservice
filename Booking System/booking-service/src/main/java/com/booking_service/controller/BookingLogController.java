package com.booking_service.controller;


import com.booking_service.entity.BookingLog;
import com.booking_service.service.BookingLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookingLogController {

    @Autowired
    private BookingLogsService bookingLogsService;

    @GetMapping("/booking-logs")
    public ResponseEntity<List<BookingLog>> getAllBookingLogs() {
        return ResponseEntity.ok(bookingLogsService.getBookingLogs());
    }

    @GetMapping("/booking-logs/{id}")
    public ResponseEntity<BookingLog> getBookingLog(@PathVariable Long id) {
        return ResponseEntity.ok(bookingLogsService.getBookingLog(id));
    }

    @GetMapping("/booking-logs/booking/{id}")
    public ResponseEntity<List<BookingLog>> getLogsForBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingLogsService.getLogsForBooking(id));
    }
}

