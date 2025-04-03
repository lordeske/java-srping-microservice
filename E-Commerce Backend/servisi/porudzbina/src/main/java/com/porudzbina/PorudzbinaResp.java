package com.porudzbina;


import lombok.Builder;

@Builder
public record PorudzbinaResp (
        Integer id,

        String referenca,

        NacinPlacanja nacinPlacanja,

        String korisnikId

        ) {







}
