package com.porudzbina;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PorudzbinaService {


    @Autowired
    private  KorisnikKlijent korisnikKlijent;


    @Autowired
    private ProizvodKlijent proizvodKlijent;

    @Autowired
    private PorudzbinaRepo porudzbinaRepo;


    private PordzbinaMapper porudzinaMapper;


    @Autowired
    private StavkePorudzbineService stavkePorudzbineService;




    public Integer kreirajPorudzbinu(PorudzbinaReq porudzbinaReq) {

        KorisnikResp korisnik = korisnikKlijent.nadjiKorisnikaPoID(porudzbinaReq.idKorisnika())
                .orElseThrow(()-> new EntityNotFoundException("Korisnik nije pronadjen"));



        proizvodKlijent.kupiProizvode(porudzbinaReq.proizvodi());



        Porudzbina porudzbina = porudzbinaRepo.save(porudzinaMapper.izReqUPordz(porudzbinaReq));


        for(KupovinaZahtjev kupovinaZahtjev : porudzbinaReq.proizvodi())
        {
            stavkePorudzbineService.sacuvajStavkePorudzbine(
                    new StavkePorudzbineReq(porudzbina.getId(),
                            kupovinaZahtjev.idProizvoda(),
                            kupovinaZahtjev.kolicinaProizvoda())
            );


        }



        //// dodati placanje kasnije



        /// dodati slanje notifikacije



        return null;
    }
}
