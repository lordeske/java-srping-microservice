package com.korisnik;

public class KorisnikResp {

    private String id;
    private String ime;
    private String prezime;
    private String email;
    private Adresa adresa;

    // Prazan konstruktor
    public KorisnikResp() {}

    // Konstruktor sa svim poljima
    public KorisnikResp(String id, String ime, String prezime, String email, Adresa adresa) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.adresa = adresa;
    }

    // Getteri
    public String getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getEmail() {
        return email;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    // Setteri
    public void setId(String id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "KorisnikResp{" +
                "id='" + id + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", email='" + email + '\'' +
                ", adresa=" + adresa +
                '}';
    }
}
