package com.proizvod;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Proizvod {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nazivProizvoda;

    private String opisProizvoda;

    private double brojDostupnih;

    private BigDecimal cena;


    @ManyToOne
    @JoinColumn(name = "id_kategorije")
    private Kategorija kategorija;


}
