package com.booking_service.service;


import com.booking_service.entity.Booking;
import com.booking_service.entity.BookingLog;
import com.booking_service.repository.BookingLogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingLogsService {



    @Autowired
    private BookingLogRepository bookingLogRepository;



    public BookingLog getBookingLog(Long id)
    {


        return bookingLogRepository.findById(id)
                .orElseThrow(
                        ()-> new EntityNotFoundException("Nema Bookinga sa tim ID")
                );

    }

    public List<BookingLog> getBookingLogs()
    {


        return bookingLogRepository.findAll();

    }



    public List<BookingLog> getLogsForBooking(Long bookingId) {
        return bookingLogRepository.findByBookingIdOrderByTimestampDesc(bookingId);
    }



}
