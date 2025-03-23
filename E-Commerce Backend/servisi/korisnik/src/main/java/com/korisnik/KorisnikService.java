package com.korisnik;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KorisnikService {


    @Autowired
    private KorisnikRepo korisnikRepo;

    @Autowired
    private KorisnikMapper korisnikMapper;

    public String kreirajKorisnika(KorisnikReq zahtjev) {

        Korisnik korisnik = korisnikRepo.save(korisnikMapper.mapirajUKorisnika(zahtjev));
        return korisnik.getId();


    }

    public void azurirajKorisnika(KorisnikReqUpdate zahtjev) {
        Optional<Korisnik> korisnikOptional = korisnikRepo.findById(zahtjev.id());

        if (korisnikOptional.isPresent()) {
            Korisnik korisnik = korisnikOptional.get();


            String noviEmail = Optional.ofNullable(zahtjev.email()).orElse(korisnik.getEmail());

            Adresa novaAdresa = Optional.ofNullable(zahtjev.adresa()).orElse(korisnik.getAdresa());

            Korisnik azuriraniKorisnik = new Korisnik(
                    korisnik.getId(),
                    korisnik.getIme(),
                    korisnik.getPrezime(),
                    noviEmail,
                    novaAdresa

            );

            korisnikRepo.save(azuriraniKorisnik);
        } else {
            throw new KorisnikNijePronadjenException("Korisnik sa ID-jem " + zahtjev.id() + " nije pronađen!");
        }
    }



    public List<KorisnikResp> sviKorisnici()
    {

        return korisnikRepo.findAll()
                .stream()
                .map(korisnik -> korisnikMapper.mapirajUResponse(korisnik))
                .collect(Collectors.toList());

    }

    public Boolean postojiLiKorisnik(String korisnikID) {

        return korisnikRepo.existsById(korisnikID);


    }

    public KorisnikResp prikaziKorisnika(String korisnikID) {

        Optional<Korisnik> korisnik =  korisnikRepo.findById(korisnikID);

        if (korisnik.isPresent())
        {
            Korisnik dobijeniKorisnik = korisnik.get();

            return korisnikMapper.mapirajUResponse(dobijeniKorisnik);


        }
        else
        {

            throw new KorisnikNijePronadjenException("Korisnik sa ID-jem " + korisnikID + " nije pronađen!");

        }


    }

    public RezultatDTO obrisiKorisika(String korisnikID) {


        Optional<Korisnik> korisnik =  korisnikRepo.findById(korisnikID);

        if (korisnik.isPresent())
        {

            korisnikRepo.deleteById(korisnikID);
            return RezultatStatus.PROSAO;

        }
        else
        {

            throw new KorisnikNijePronadjenException("Korisnik sa ID-jem " + korisnikID + " nije pronađen!");

        }




    }
}
