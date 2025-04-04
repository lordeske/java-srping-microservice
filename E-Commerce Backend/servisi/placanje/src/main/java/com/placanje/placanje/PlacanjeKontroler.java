package com.placanje.placanje;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/placanje")
public class PlacanjeKontroler {



    @Autowired
    private  PlacanjeService placanjeService;


    @Autowired
    private   PlacanjeMapper placanjeMapper;

    @PostMapping
    public ResponseEntity<Integer> kreirajPlacanje(
            @RequestBody @Valid PlacanjeReq placanjeReq
    )
    {


        return ResponseEntity.ok(placanjeService.kreirajPlacanje(placanjeReq));

    }



}
