package com.korisnik;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/korisnik")

public class KorisnikKontroler {


    @Autowired

    private KorisnikService korisnikService;



    @PostMapping
    public ResponseEntity<String> kreirajKorisnika(
            @RequestBody @Valid KorisnikReq zahtjev
    )
    {


        return ResponseEntity.ok(korisnikService.kreirajKorisnika(zahtjev));


    }


    @PutMapping
    public ResponseEntity<RezultatDTO> azurirajKorisnika(
            @RequestBody @Valid KorisnikReqUpdate zahtjev
    )
    {

        korisnikService.azurirajKorisnika(zahtjev);

        return ResponseEntity.ok(RezultatStatus.PROSAO);
    }


    @GetMapping
    public ResponseEntity<List<KorisnikResp>> sviKorisnici()
    {

        return  ResponseEntity.ok(korisnikService.sviKorisnici());
    }


    @GetMapping("/postoji/{korisnik-id}")
    public ResponseEntity<Boolean>  postojiLiKorisnik(
            @PathVariable("korisnik-id") String korisnikID
    )
    {


        return ResponseEntity.ok(korisnikService.postojiLiKorisnik(korisnikID));



    }

    @GetMapping("/{korisnik-id}")
    public ResponseEntity<KorisnikResp>  prikaziKorisnika(
            @PathVariable("korisnik-id") String korisnikID
    )
    {


        return ResponseEntity.ok(korisnikService.prikaziKorisnika(korisnikID));



    }


    @DeleteMapping("/{korisnik-id}")
    public ResponseEntity<RezultatDTO>  obrisiKorisnika(
            @PathVariable("korisnik-id") String korisnikID
    )
    {

        RezultatDTO status =  korisnikService.obrisiKorisika(korisnikID);
        return new ResponseEntity<>(status, HttpStatus.OK);



    }






}
