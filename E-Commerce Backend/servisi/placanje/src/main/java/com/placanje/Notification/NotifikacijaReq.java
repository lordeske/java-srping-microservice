package com.placanje.Notification;

import com.placanje.placanje.NacinPlacanja;

import java.math.BigDecimal;

public record NotifikacijaReq
        (

                String referencaPorudzbine,
                BigDecimal ukupnCena,

                NacinPlacanja nacinPlacanja,

                String imeKorisnika,

                String prezimeKorisnika,

                String emailKorisnika

        ){
}
