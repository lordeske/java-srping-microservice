package com.porudzbina;


import jakarta.validation.Valid;
import org.checkerframework.common.value.qual.EnsuresMinLenIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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




    @GetMapping
    public ResponseEntity<List<PorudzbinaResp>> svePorudzbine()
    {

        return ResponseEntity.ok( porudzbinaService.nadjiSve());
    }


    @GetMapping("/{porudzbina-id}")
    public ResponseEntity <PorudzbinaResp> nadjiPorudzbinu(
            @PathVariable("porudzbina-id") Integer  porudzbinaId
    )
    {

        return ResponseEntity.ok( porudzbinaService.nadjiPorudzbinu(porudzbinaId));
    }


}
