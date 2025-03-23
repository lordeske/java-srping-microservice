package com.korisnik;


import org.springframework.stereotype.Service;

@Service
public class KorisnikMapper {
    public Korisnik mapirajUKorisnika(KorisnikReq zahtjev) {

        if (zahtjev == null) {
            throw new RuntimeException("Kreiranje korisnika ne valja");
        }

        return new Korisnik(
                null, // id je null jer se automatski generiše (pretpostavljam)
                zahtjev.ime(),
                zahtjev.prezime(),
                zahtjev.email(),
                zahtjev.adresa()
        );
    }



    public KorisnikResp mapirajUResponse(Korisnik korisnik)
    {

        KorisnikResp korisnikResp = new KorisnikResp();
        korisnikResp.setId(korisnik.getId());
        korisnikResp.setIme(korisnik.getIme());
        korisnikResp.setAdresa(korisnik.getAdresa());
        korisnikResp.setEmail(korisnik.getEmail());

        return korisnikResp;




    }

}
