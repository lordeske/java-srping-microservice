package com.porudzbina;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Table(name = "korisnik_porudzbina")
@EntityListeners(AuditingEntityListener.class)
public class Porudzbina {


    @Id
    @GeneratedValue
    private Integer id;

    private String referenca;

    private BigDecimal ukupnaCena;


    @Enumerated(EnumType.STRING)
    private NacinPlacanja nacinPlacanja;

    private String idKorisnika;


    @OneToMany(mappedBy = "porudzbina")
    private List<StavkePorudzbine> stavkePorudzbine;

    @Column(updatable = false, nullable = false)
    @CreatedDate
    private LocalDateTime datumKreiranja;

    @LastModifiedDate
    private LocalDateTime datumUredjivanja;

}
