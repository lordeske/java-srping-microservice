package com.korisnik;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;




public record KorisnikReq(


        @NotBlank(message = "Korisnicko ime je obavezno")
        String ime,
        @NotBlank(message = "Prezime ime je obavezno")
        String prezime,
        @NotBlank(message = "Email ime je obavezan")
        @Email(message = "Email nije validan")
        String email,


        @Valid
        Adresa adresa
) {}
