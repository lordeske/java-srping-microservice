package com.porudzbina;

import org.springframework.stereotype.Service;

@Service
public class PordzbinaMapper {


    public Porudzbina izReqUPordz(PorudzbinaReq porudzbinaReq) {

       return Porudzbina.builder()
               .idKorisnika(porudzbinaReq.idKorisnika())
               .referenca(porudzbinaReq.referenca())
               .ukupnaCena(porudzbinaReq.ukupnaCena())
               .nacinPlacanja(porudzbinaReq.nacinPlacanja())
               .build();



    }
}
