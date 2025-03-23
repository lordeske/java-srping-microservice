package com.korisnik;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


public record KorisnikReqUpdate(

        @NotNull(message = "ID je obavezan")
        String id,


        @Email(message = "Email nije validan")
        String email,


        @Valid
        Adresa adresa
) {}
