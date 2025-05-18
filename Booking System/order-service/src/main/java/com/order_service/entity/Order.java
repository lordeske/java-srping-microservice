package com.order_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "\"order\"")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "quantity")
    private Long ticketCount;

    @CreationTimestamp
    @Column(name = "placed_at" , updatable = false)
    private LocalDateTime placedAt;

    @Column(name = "booking_id")
    private Long bookingId;


    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "event_id")
    private Long eventId;





}
