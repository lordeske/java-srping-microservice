package com.porudzbina;

import java.math.BigDecimal;

public record KupljeniProizovdiResp(

        Integer idProizvoda,

        String nazivProizvoda,

        String opisProizvoda,

        BigDecimal cenaProizvoda,



        double kolicinaProizvoda





) {
}
