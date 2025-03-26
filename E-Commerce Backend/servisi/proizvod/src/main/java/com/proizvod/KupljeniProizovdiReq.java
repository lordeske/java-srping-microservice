package com.proizvod;

import jakarta.validation.constraints.NotNull;

public record KupljeniProizovdiReq (
        @NotNull(message = "ProizvodID je obavezan")
        Integer idProizvoda,



        @NotNull(message = "Kolicina proizvoda je obavezna")
        double kolicina
){
}
