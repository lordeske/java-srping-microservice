package com.booking_service;

import com.booking_service.entity.Customer;
import com.booking_service.repository.CustomerRepository;
import com.booking_service.request.BookingRequest;
import com.booking_service.response.BookingResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {


    @Autowired
    private CustomerRepository customerRepository;




    public BookingResponse createBooking(BookingRequest bookingRequest) {


        Customer customer = customerRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Customer sa ID: " + bookingRequest.getUserId()
                        + " nije pronadjen"));






        return null;
    }
}
