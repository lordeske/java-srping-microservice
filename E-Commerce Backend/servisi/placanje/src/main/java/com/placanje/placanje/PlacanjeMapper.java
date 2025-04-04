package com.placanje.placanje;

public class PlacanjeMapper {
    public Placanje uPlacanje(PlacanjeReq placanjeReq) {

        return Placanje.builder()
                .nacinPlacanja(placanjeReq.nacinPlacanja())
                .idPorudzbine(placanjeReq.idPorudzbine())
                .ukupnaCena(placanjeReq.ukupnaCena())
                .build();
    }
}
