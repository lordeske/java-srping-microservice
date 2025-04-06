package com.notifikacija.Email;

import lombok.Getter;

@Getter
public enum EmailTemplate {

    POTVRDA_PLACANJA("potvrda-placanja.html", "Placanje uspesno obavljeno"),
    POTVRDA_PORUDZBINE("potvrda-porudzbine.html", "Porudzbina uspesno obavljena")
    ;


    private final String template;


    private  final String naslov;


    EmailTemplate(String template, String naslov) {
        this.template = template;
        this.naslov = naslov;
    }
}
