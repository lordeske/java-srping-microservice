package com.placanje.placanje;


import com.placanje.Notification.NotifikacijaProducer;
import com.placanje.Notification.NotifikacijaReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlacanjeService {


    @Autowired
    private PlacanjeRepo placanjeRepo;

    @Autowired
    private PlacanjeMapper mapper;


    @Autowired
    private NotifikacijaProducer notifikacijaProducer;




    public Integer kreirajPlacanje(PlacanjeReq placanjeReq) {


        Placanje placanje = placanjeRepo.save(mapper.uPlacanje(placanjeReq));


        notifikacijaProducer.posaljiNotifikaciju(

                new NotifikacijaReq(
                        placanjeReq.referencaPorudzbine(),
                        placanjeReq.ukupnaCena(),
                        placanjeReq.nacinPlacanja(),
                        placanjeReq.korisnik().ime(),
                        placanjeReq.korisnik().prezime(),
                        placanjeReq.korisnik().email()


                )


        );


        return placanje.getId();
    }
}
