package com.porudzbina;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stavke-porudzbine")
public class StakePorudzbineKontroler {


    private StavkePorudzbineService service;


    @GetMapping("/stavke/{porudzbina-id}")
    public ResponseEntity<List<StavkePorudzbineResp>> nadjiPoOrderIDu(
            @PathVariable("porudzbina-id") Integer orderID
    )
    {


        return ResponseEntity.ok(service.nadjiPoOrderIDu(orderID));


    }









}
