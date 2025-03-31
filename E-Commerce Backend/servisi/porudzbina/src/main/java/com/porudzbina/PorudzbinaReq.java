package com.porudzbina;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public record PorudzbinaReq (



        String referenca,


        @Positive(message = "Mora imati pozitivnu ukupnu cenu")
        BigDecimal ukupnaCena,


        @NotNull(message = "Mora imati nacin placanja")
        NacinPlacanja nacinPlacanja,


        @NotNull(message = "Mora postojati korisnik")
        @NotBlank(message = "Mora postojati korisnik")
        @NotEmpty(message = "Mora postojati korisnik")
        String idKorisnika,


        @NotEmpty(message = "Moras kupiti bar neki proizvod")
        List<KupovinaZahtjev> proizvodi




){





}
