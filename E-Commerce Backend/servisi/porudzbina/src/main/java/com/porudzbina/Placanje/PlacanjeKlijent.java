package com.porudzbina.Placanje;


import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "placanje-service",
        url =  "${application.config.placanje-url}"

)
public interface PlacanjeKlijent {



    @PostMapping
    Integer zatraziPlacanjePorudzbine(
            @RequestBody @Valid PlacanjeReq placanjeReq
    );







}
