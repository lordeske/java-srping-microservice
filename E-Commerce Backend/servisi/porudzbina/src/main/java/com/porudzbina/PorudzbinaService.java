package com.porudzbina;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PorudzbinaService {


    @Autowired
    private  KorisnikKlijent korisnikKlijent;


    private ProizvodKlijent proizvodKlijent;

    private

    public Integer kreirajPorudzbinu(PorudzbinaReq porudzbinaReq) {

        KorisnikResp korisnik = korisnikKlijent.nadjiKorisnikaPoID(porudzbinaReq.idKorisnika())
                .orElseThrow(()-> new EntityNotFoundException("Korisnik nije pronadjen"));





    }
}
