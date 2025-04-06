package com.notifikacija.Kafka.Placanje;

import java.math.BigDecimal;

public record PotvrdaPlacanja(


        String referencaPorudzbine,

        BigDecimal ukupnaCena,

        NacinPlacanja nacinPlacanja,

        String imeKorisnka,
        String prezimeKoirsnika,
        String emailKorisnika





) {
}
