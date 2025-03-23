package com.korisnik;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document
@Builder
public class Adresa {

    @NotBlank(message = "Ulica je obavezna")
    private String ulica;

    @NotBlank(message = "Grad je obavezan")
    private String brojApartmana;

    private String postanskiBroj;
}
