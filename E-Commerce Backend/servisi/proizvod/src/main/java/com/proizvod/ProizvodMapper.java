package com.proizvod;

public class ProizvodMapper {



    public Proizvod uProizvod(ProzivodReq prozivodReq)
    {

        return Proizvod.builder()
                .id(null)
                .brojDostupnih(prozivodReq.brojDostupnih())
                .cena(prozivodReq.cena())
                .nazivProizvoda(prozivodReq.nazivProizvoda())
                .opisProizvoda(prozivodReq.opisProizvoda())
                .kategorija(Kategorija.builder().id(prozivodReq.kategorijaID()).build())

                .build();


    }


    public ProzivodResp uResp(Proizvod dobijeniProizvod) {

       return new  ProzivodResp(
               dobijeniProizvod.getId(),
               dobijeniProizvod.getNazivProizvoda(),
               dobijeniProizvod.getOpisProizvoda(),
               dobijeniProizvod.getBrojDostupnih(),
               dobijeniProizvod.getCena(),
               dobijeniProizvod.getKategorija().getId(),
               dobijeniProizvod.getKategorija().getNazivKategorije(),
               dobijeniProizvod.getKategorija().getOpisKategorije()
       );





    }
}
