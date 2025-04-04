package com.placanje.placanje;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Korisnik(
        String id,

        @NotNull(message = "Mora imati ime")
        String ime,

        @NotNull(message = "Mora imati prezime")
        String prezime,

        @NotNull(message = "Mora imati emai")
        @Email(message = "Mail nije kakav treba da bude")
        String email
) {





}
