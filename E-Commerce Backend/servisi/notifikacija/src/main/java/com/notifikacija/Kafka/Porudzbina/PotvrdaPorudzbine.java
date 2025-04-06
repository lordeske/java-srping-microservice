package com.notifikacija.Kafka.Porudzbina;

import com.notifikacija.Kafka.Placanje.NacinPlacanja;

import java.math.BigDecimal;
import java.util.List;

public record PotvrdaPorudzbine(

        String referencaPorudzbine,
        BigDecimal ukupnCena,

        NacinPlacanja nacinPlacanja,

        Korisnik korisnik,

        List<Proizvod> proizvodi


) {
}
