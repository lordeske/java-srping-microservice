package com.placanje.placanje;

import java.math.BigDecimal;

public record PlacanjeReq
        (


                Integer id,

                BigDecimal ukupnaCena,
                NacinPlacanja nacinPlacanja,

                Integer idPorudzbine,

                String referencaPorudzbine,

                Korisnik korisnik

        ){
}
