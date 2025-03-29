package com.porudzbina;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "korisnik-service",
        url = "${application.config.customer-url}"
)
public interface KorisnikKlijent {



    @GetMapping("/{korisnik-id}")
    Optional<KorisnikResp> nadjiKorisnikaPoID(
            @PathVariable("korisnik-id") String korisnikID);







}
