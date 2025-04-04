package com.porudzbina.Placanje;

import com.porudzbina.KorisnikResp;
import com.porudzbina.NacinPlacanja;

import java.math.BigDecimal;

public record PlacanjeReq
        (

                BigDecimal ukupnaCena,
                NacinPlacanja nacinPlacanja,

                Integer idPorudzbine,

                String referencaPorudzbine,

                KorisnikResp korisnik

        ){
}
