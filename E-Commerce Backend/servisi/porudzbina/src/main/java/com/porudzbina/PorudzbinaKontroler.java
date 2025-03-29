package com.porudzbina;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/porudzbine")
public class PorudzbinaKontroler {

    @Autowired
    private PorudzbinaService porudzbinaService;



    @PostMapping
    private ResponseEntity<Integer> kreirajPorudzbinu
            (

                    @RequestBody @Valid  PorudzbinaReq porudzbinaReq

            )


    {


        return ResponseEntity.ok(porudzbinaService.kreirajPorudzbinu(porudzbinaReq));

    }





}
