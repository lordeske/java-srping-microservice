package com.booking_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@Table(name = "booking_logs")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BookingLog {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookingId;

    private String status;

    private String action;

    private LocalDateTime timestamp;

    private String description;





}
