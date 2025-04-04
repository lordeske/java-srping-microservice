package com.porudzbina;


import com.porudzbina.Kafka.PorudzbinaProducer;
import com.porudzbina.Kafka.PotvrdaPorudzbine;
import com.porudzbina.Placanje.PlacanjeKlijent;
import com.porudzbina.Placanje.PlacanjeReq;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    @Autowired
    private PorudzbinaProducer producer;

    @Autowired

    private PlacanjeKlijent placanjeKlijent;




    public Integer kreirajPorudzbinu(PorudzbinaReq porudzbinaReq) {

        KorisnikResp korisnik = korisnikKlijent.nadjiKorisnikaPoID(porudzbinaReq.idKorisnika())
                .orElseThrow(()-> new EntityNotFoundException("Korisnik nije pronadjen"));



        List<KupljeniProizovdiResp> kupljeniProizovdiResp =  proizvodKlijent.kupiProizvode(porudzbinaReq.proizvodi());



        Porudzbina porudzbina = porudzbinaRepo.save(porudzinaMapper.izReqUPordz(porudzbinaReq));


        for(KupovinaZahtjev kupovinaZahtjev : porudzbinaReq.proizvodi())
        {
            stavkePorudzbineService.sacuvajStavkePorudzbine(
                    new StavkePorudzbineReq(porudzbina.getId(),
                            kupovinaZahtjev.idProizvoda(),
                            kupovinaZahtjev.kolicinaProizvoda())
            );


        }


        PlacanjeReq novoPlacanje = new PlacanjeReq(
                porudzbinaReq.ukupnaCena(),
                porudzbinaReq.nacinPlacanja(),
                porudzbina.getId(),
                porudzbina.getReferenca(),
                korisnik
        );

        placanjeKlijent.zatraziPlacanjePorudzbine(novoPlacanje);




        /// slanje notifikacije
        producer.posaljiPotvrduPorudzbine(
                new PotvrdaPorudzbine(

                        porudzbinaReq.referenca(),
                        porudzbinaReq.ukupnaCena(),
                        porudzbinaReq.nacinPlacanja(),
                        korisnik,
                        kupljeniProizovdiResp







                )
        );


      return  porudzbina.getId();
    }

    public List<PorudzbinaResp> nadjiSve() {


        return porudzbinaRepo.findAll()
                .stream()
                .map(porudzinaMapper :: uResponse )
                .collect(Collectors.toList());
    }

    public PorudzbinaResp nadjiPorudzbinu(Integer porudzbinaId) {

        return porudzbinaRepo.findById(porudzbinaId)
                .map(porudzinaMapper :: uResponse)
                .orElseThrow(() -> new EntityNotFoundException("Ne postoji porudzbina sa tim ID " + porudzbinaId));

    }
}
