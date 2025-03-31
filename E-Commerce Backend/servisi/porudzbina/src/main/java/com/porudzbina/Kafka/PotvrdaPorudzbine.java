package com.porudzbina.Kafka;

import com.porudzbina.KorisnikResp;
import com.porudzbina.KupljeniProizovdiResp;
import com.porudzbina.NacinPlacanja;

import java.math.BigDecimal;
import java.util.List;

public record PotvrdaPorudzbine (

        String referncaPorduzbine,


        BigDecimal ukupnaCena,

        NacinPlacanja nacinPlacanja,

        KorisnikResp korisnik,

        List<KupljeniProizovdiResp> proizvodi





){






}
