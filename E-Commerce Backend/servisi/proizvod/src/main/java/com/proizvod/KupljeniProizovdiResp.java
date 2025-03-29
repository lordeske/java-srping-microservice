package com.proizvod;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;


public record KupljeniProizovdiResp (



        Integer idProizvoda,

        String nazivProizvoda,

        String opisProizvoda,

        BigDecimal cenaProizvoda,



        double kolicinaProizvoda


){
}
