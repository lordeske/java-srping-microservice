package com.porudzbina;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class StavkePorudzbine {

    @Id
    @GeneratedValue
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "id_porudzbine")
    private Porudzbina porudzbina;

    private Integer idProizvoda;
    private double kolicina;



}
