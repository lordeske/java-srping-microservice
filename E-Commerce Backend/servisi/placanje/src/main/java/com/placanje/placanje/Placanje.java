package com.placanje.placanje;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "placanje")
public class Placanje {

    @Id
    @GeneratedValue
    private Integer id;

    private BigDecimal ukupnaCena;

    @Enumerated(EnumType.STRING)
    private NacinPlacanja nacinPlacanja;

    private Integer idPorudzbine;

    @Column(updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime datumKreiranja;

    @LastModifiedDate
    private LocalDateTime datumUredjivanja;









}
