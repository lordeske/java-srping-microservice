package com.notifikacija.notifikacija;


import com.notifikacija.Email.EmailServis;
import com.notifikacija.Kafka.Placanje.PotvrdaPlacanja;
import com.notifikacija.Kafka.Porudzbina.PotvrdaPorudzbine;
import com.notifikacija.notifikacija.Notifikacija;
import com.notifikacija.notifikacija.NotifikacijaRepo;
import com.notifikacija.notifikacija.TipNotifikacije;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class NotifikacijaKonzumer {



    @Autowired
    private NotifikacijaRepo notifikacijaRepo;


    @Autowired
    private EmailServis emailServis;


    @KafkaListener(topics = "placanje-topic", groupId = "grupa-placanja")
    public void konzumirajPotvrduPlacanja(PotvrdaPlacanja potvrdaPlacanja) throws MessagingException {

        log.info(String.format("Pocinjem sa radom:: %s", potvrdaPlacanja));


        notifikacijaRepo.save(
                Notifikacija.builder()
                        .tipNotifikacije(TipNotifikacije.POTVRDA_PLACANJA)
                        .datumNotifikacije(LocalDateTime.now())
                        .potvrdaPlacanja(potvrdaPlacanja)
                        .build()
        );



        /// slanje maila

        String fullImeiPrezime = potvrdaPlacanja.imeKorisnka() + " " + potvrdaPlacanja.prezimeKoirsnika();

        emailServis.posaljiPotvrduPlacanjaEmail(
                potvrdaPlacanja.emailKorisnika(),
                fullImeiPrezime,
                potvrdaPlacanja.ukupnaCena(),
                potvrdaPlacanja.referencaPorudzbine()
        );






    }




    @KafkaListener(topics = "porudzbina-topic" , groupId = "grupa-porucivanja")
    public void konzumirajPotvrduPorudzbine(PotvrdaPorudzbine potvrdaPorudzbine) throws MessagingException {

        log.info(String.format("Pocinjem sa radom:: %s", potvrdaPorudzbine));


        notifikacijaRepo.save(
                Notifikacija.builder()
                        .tipNotifikacije(TipNotifikacije.POTVRDA_PORUDZBINE)
                        .datumNotifikacije(LocalDateTime.now())
                        .potvrdaPorudzbine(potvrdaPorudzbine)
                        .build()
        );



        /// posalti emamail

        String fullImeiPrezime = potvrdaPorudzbine.korisnik().ime() + " " + potvrdaPorudzbine.korisnik().prezime();

        emailServis.posaljiPotvrduPorudzbineEmail(
                potvrdaPorudzbine.korisnik().email(),
                fullImeiPrezime,
                potvrdaPorudzbine.ukupnCena(),
                potvrdaPorudzbine.referencaPorudzbine(),
                potvrdaPorudzbine.proizvodi()
        );
     






    }


}
