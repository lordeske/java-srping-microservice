package com.korisnik;

public class RezultatStatus {
    public static final RezultatDTO PROSAO = new RezultatDTO(0, "prošao");
    public static final RezultatDTO NIJE_PROSAO = new RezultatDTO(1, "nije prošao");
    public static final RezultatDTO GRESKA = new RezultatDTO(-1, "došlo je do greške");

    private RezultatStatus() {}
}
