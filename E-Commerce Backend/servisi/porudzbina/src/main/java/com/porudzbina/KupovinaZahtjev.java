package com.porudzbina;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record KupovinaZahtjev (

        @NotEmpty(message = "Proizvod je obavezan")
        Integer idProizvoda,

        @Positive(message = "Kolicina je obavezna")
        double kolicinaProizvoda



){

}
