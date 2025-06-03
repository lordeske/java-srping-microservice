package com.booking_service.repository;

import com.booking_service.entity.Booking;
import com.booking_service.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByStatusAndCreatedAtBefore(BookingStatus bookingStatus, LocalDateTime expirationTime);
}
