package com.booking_service.repository;

import com.booking_service.entity.BookingLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingLogRepository extends JpaRepository<BookingLog, Long> {


    List<BookingLog> findByBookingIdOrderByTimestampDesc(Long bookingId);
}
