package com.proizvod;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProzivodReq(


        @NotNull(message = "Proizvod mora imati ime")
        String nazivProizvoda,

        @NotNull(message = "Proizvod mora imati opis")
        String opisProizvoda,

        @Positive(message = "Mora imati pozitivnu neki na stanju")
        double brojDostupnih,

        @Positive(message = "Mora imati pozitivnu cenu")
        BigDecimal cena,

        @NotNull(message = "Morati pripadati nekoj kategoriji")
        Integer kategorijaID
) {}
