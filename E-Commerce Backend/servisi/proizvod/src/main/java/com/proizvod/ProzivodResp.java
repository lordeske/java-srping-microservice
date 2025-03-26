package com.proizvod;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProzivodResp (

        Integer idProizvoda,

        String nazivProizvoda,


        String opisProizvoda,


        double brojDostupnih,


        BigDecimal cena,


        Integer kategorijaID,

        String nazivKategorije,

        String opisKategorije
){



}
