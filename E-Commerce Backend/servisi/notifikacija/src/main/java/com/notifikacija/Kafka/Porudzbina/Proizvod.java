package com.notifikacija.Kafka.Porudzbina;


import java.math.BigDecimal;

public record Proizvod (


      Integer id,

      String nazivProizvoda,

      String opisProizvoda,

      double brojDostupnih,

      BigDecimal cena,


      double kolicina
){
}
